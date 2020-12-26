package com.github.standardplugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogClassVisitor(classVisitor: ClassVisitor? = null) : ClassVisitor(Opcodes.ASM6, classVisitor) {
    private var owner: String? = null
    private var isInterface: Boolean = false
    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        owner = name
        isInterface = (access and Opcodes.ACC_INTERFACE) != 0
    }

    override fun visitMethod(access: Int, name: String, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        val mv = cv?.visitMethod(access, name, descriptor, signature, exceptions)
        println("mv = ${mv?.toString() ?: "null"}")
        if (!isInterface && mv !== null && (name != "<init>" || name != "<clinit>")) {
            return LogMethodVisitor(mv, name)
        }
        return mv
    }
}