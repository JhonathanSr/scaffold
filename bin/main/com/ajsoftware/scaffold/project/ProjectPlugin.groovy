package com.ajsoftware.scaffold.project

import org.gradle.api.Plugin
import org.gradle.api.Project

class ProjectPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.register('createProject', CreateProjectTask) {
            group = 'AJSoftware Scaffolding'
            description = 'Crea el enrutamiento base del proyecto'
        }

        project.tasks.register('cp') {
            group = 'AJSoftware Scaffolding'
            description = 'Alias de createProject'
            dependsOn 'createProject'
            doFirst {
                println '''
📦 cp - Alias de createProject
Ejemplo:
gradle cp -Pname=inventory -Ppackage=com.aj.inventory -PjavaVersion=17
'''
            }
        }

        project.tasks.register('projectHelp') {
            group = 'Scaffolding'
            description = 'Ejemplos de uso para createProject'

            doLast {
                println '📦 gradle cp -Pname=inventory -Ppackage=com.aj.inventory -PjavaVersion=17'
            }
        }
    }

}
