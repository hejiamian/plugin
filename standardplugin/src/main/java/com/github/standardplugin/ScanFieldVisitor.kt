package com.github.standardplugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Attribute
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Opcodes

class ScanFieldVisitor(fv: FieldVisitor) : FieldVisitor(Opcodes.ASM6, fv) {
    private var mExistDesc: String? = null
    override fun visitAnnotation(desc: String, visible: Boolean): AnnotationVisitor {
        println("visitAnnotation = $desc, $visible")
        mExistDesc = desc
        return super.visitAnnotation(desc, visible)
    }

    override fun visitAttribute(attr: Attribute?) {
        println("visitAttribute")
        super.visitAttribute(attr)
    }

    override fun visitEnd() {
        println("visitEnd")
        val desc = "Lorg/jetbrains/annotations/NotNull;"
        if (desc != mExistDesc)
            fv.visitAnnotation("Lorg.jetbrains.annotations.NotNull;", false)
        super.visitEnd()
    }
}