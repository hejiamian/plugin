package com.github.standardplugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogMethodVisitor(mv: MethodVisitor?, private val methodName: String) : MethodVisitor(Opcodes.ASM6, mv) {
    override fun visitCode() {
        println("at Method ‘${methodName}’ Begin...")
        super.visitCode()
        mv.visitCode()
    }

    override fun visitMethodInsn(p0: Int, p1: String?, p2: String?, p3: String?, p4: Boolean) {
        println("visitMethodInsn at Method ‘${methodName}")
        super.visitMethodInsn(p0, p1, p2, p3, p4)
    }

    override fun visitEnd() {
        println("at Method ‘${methodName}’ End...")
        super.visitEnd()
    }
}