package com.github.standardplugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogClassVisitor(classVisitor: ClassVisitor? = null) : ClassVisitor(Opcodes.ASM6, classVisitor) {
    private var owner: String? = null
    private var isInterface: Boolean = false
    private var isAbstract: Boolean = false
    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        owner = name
        isInterface = (access and Opcodes.ACC_INTERFACE) != 0
        isAbstract = (access and Opcodes.ACC_ABSTRACT) != 0
    }

    override fun visitMethod(access: Int, name: String, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        val mv = cv?.visitMethod(access, name, descriptor, signature, exceptions)
        println("mv = ${mv?.toString() ?: "null"}")
        if (!isAbstract && !isInterface && mv !== null && name != owner && (name != "<init>" || name != "<clinit>")) {
            return LogMethodVisitor(mv, name)
        }
        return mv
    }

    override fun visitField(access: Int, name: String, desc: String, signature: String?, value: Any?): FieldVisitor? {
        val vf = cv?.visitField(access, name, desc, signature, value)
        println("field name = $name")
        if (vf !== null && ((access and (Opcodes.ACC_STATIC or Opcodes.ACC_FINAL) != 0)
                || (access and Opcodes.ACC_STATIC) != 0) && desc.contains('L')) {
            return ScanFieldVisitor(vf)
        }
        return vf
    }
}