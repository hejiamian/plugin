package com.github.standardplugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.objectweb.asm.ClassReader


class LogTransform : Transform() {
    override fun getName() = "LogPlugin"

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation) {
        super.transform(transformInvocation)
        val inputs = transformInvocation.inputs
        val outputProvider = transformInvocation.outputProvider

        inputs.forEach { input ->
            input.jarInputs.forEach {
                val dest = outputProvider.getContentLocation(it.name, it.contentTypes,
                        it.scopes, Format.JAR)
                println("jarInputs file name = ${it.file.name}")
//                FileUtils.copyFile(it.file, dest)
            }

            input.directoryInputs.forEach {
                val dest = outputProvider.getContentLocation(it.name, it.contentTypes,
                        it.scopes, Format.DIRECTORY)
                println("directoryInputs file name = ${it.name}")
//                val cr = ClassReader(it.file.name)
//                cr.accept(LogClassVisitor(), ClassReader.SKIP_DEBUG)
//                FileUtils.copyDirectory(it.file, dest)
            }
        }
    }
}