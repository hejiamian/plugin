package com.github.standardplugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class StandardPlugin : Plugin<Project> {
    override fun apply(project: Project) {
//        project.task("showStandardPluginProjectName") {
//            println("StandardPlugin: " + project.name)
//        }
        val extension = project.extensions.create("standardplugin",
                LogExtension::class.java)
        project.afterEvaluate {
            println("extension name = ${extension.name}")
        }
        val transform = LogTransform()
        val baseExtension = project.extensions.getByType(BaseExtension::class.java)
        baseExtension.registerTransform(transform)
    }
}