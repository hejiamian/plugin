package com.github.buildplugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class BuildSrcPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.task("showBuildSrcProjectName", new Action<Task>() {
            @Override
            public void execute(Task task) {
                System.out.println("BuildSrcPlugin: " + project.getName());
            }
        });
    }
}