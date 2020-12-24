package com.github.standardplugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class LogMethodVisitor(mv: MethodVisitor?, private val methodName: String) : MethodVisitor(Opcodes.ASM5, mv) {
    override fun visitCode() {
        super.visitCode()
    }

    override fun visitMethodInsn(p0: Int, p1: String?, p2: String?, p3: String?, p4: Boolean) {
        super.visitMethodInsn(p0, p1, p2, p3, p4)
    }

    override fun visitEnd() {
        super.visitEnd()
    }
}