package com.ajsoftware.scaffold.module

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException

class ModulePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Tarea principal
        project.tasks.register('generateModule', GenerateModuleTask) {
            group = 'AJSoftware Scaffolding'
            description = 'Genera un módulo funcional con estructura Clean Architecture'
        }

        // Alias gm como clase dedicada
        project.tasks.register('gm', GmAliasTask) {
            group = 'AJSoftware Scaffolding'
            description = 'Alias de generateModule'
            dependsOn 'generateModule'
        }

        // Validación de parámetros en tiempo de ejecución
        project.gradle.taskGraph.whenReady { graph ->
            if (graph.hasTask(':generateModule') || graph.hasTask(':gm')) {
                if (!project.hasProperty('name') || project.property('name').trim().isEmpty()) {
                    throw new GradleException('❌ Falta el parámetro --name. Ejemplo: gradle gm --name=inventory')
                }
            }
        }
    }

}
