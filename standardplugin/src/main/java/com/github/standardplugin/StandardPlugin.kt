package com.github.standardplugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class StandardPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.task("showStandardPluginProjectName") { println("StandardPlugin: " + project.name) }
    }
}