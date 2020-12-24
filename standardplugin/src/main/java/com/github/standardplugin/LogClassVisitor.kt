package com.github.standardplugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogClassVisitor(private var cv: ClassVisitor? = null) : ClassVisitor(Opcodes.ASM5, cv) {
    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitMethod(access: Int, name: String, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        val vm = super.visitMethod(access, name, descriptor, signature, exceptions)
//        if (vm !== null) {
        return LogMethodVisitor(vm, name)
//        }
//        return vm
    }
}