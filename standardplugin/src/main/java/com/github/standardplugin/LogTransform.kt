package com.github.standardplugin

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import proguard.classfile.ClassPool
import java.io.File


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
//        super.transform(transformInvocation)
        val inputs = transformInvocation.inputs
        val outputProvider = transformInvocation.outputProvider

        inputs.forEach { input ->
            input.directoryInputs.forEach {
                val dest = outputProvider.getContentLocation(it.name, it.contentTypes,
                        it.scopes, Format.DIRECTORY)
                traversal(it.file)
                println("directory name = ${it.file.name}")
//                val cr = ClassReader(it.file.name)
//                cr.accept(LogClassVisitor(), ClassReader.SKIP_DEBUG)
                FileUtils.copyDirectory(it.file, dest)
            }

            input.jarInputs.forEach {
                val dest = outputProvider.getContentLocation(it.name, it.contentTypes,
                        it.scopes, Format.JAR)
                println("jar name = ${it.file.name}")
                FileUtils.copyFile(it.file, dest)
            }
        }
    }

    private fun traversal(file: File) {
        if (file.isFile){
            println(file.name)
        } else {
            val files = file.listFiles()
            files.forEach {
                traversal(it)
            }
        }
//
//        try {
//            Files.walkFileTree(path, object : SimpleFileVisitor<Path>() {
//                override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes?): FileVisitResult {
//                    if (dir === path) {
//                        return FileVisitResult.CONTINUE
//                    }
//                    traversal(dir, depth)
//                    return FileVisitResult.CONTINUE
//                }
//
//                override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
//                    val file = path.toFile()
//                    val name = file.name
//                    println("file name = $name")
//                    return FileVisitResult.CONTINUE
//                }
//            })
//        } catch (ex: IOException) {
//            ex.printStackTrace()
//        }
    }
}