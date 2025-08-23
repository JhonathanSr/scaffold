package com.ajsoftware.scaffold.delete

import org.gradle.api.Plugin
import org.gradle.api.Project

class DeletePlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.tasks.register('deleteModule', DeleteModuleTask) {
            group = 'AJSoftware Scaffolding'
            description = 'Elimina un módulo y sus referencias'
        }

        project.tasks.register('dm') {
            group = 'AJSoftware Scaffolding'
            description = 'Alias de deleteModule'
            dependsOn 'deleteModule'
            doFirst {
                println '''
📦 dm - Alias de deleteModule
Ejemplo:
gradle dm --name=inventory
'''
            }
        }

        project.tasks.register('deleteHelp') {
            group = 'Scaffolding'
            description = 'Ejemplos de uso para deleteModule'

            doLast {
                println "🧹 gradle dm --moduleName=inventory"
            }
        }
    }
}