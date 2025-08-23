package com.ajsoftware.scaffold

import com.ajsoftware.scaffold.delete.DeleteModuleTask
import com.ajsoftware.scaffold.module.GenerateModuleTask
import com.ajsoftware.scaffold.project.CreateProjectTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class ScaffoldingPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply('com.ajsoftware.scaffold.project')
        project.pluginManager.apply('com.ajsoftware.scaffold.module')

        project.tasks.register('scaffoldHelp') {
            group = 'Scaffolding'
            description = 'Muestra ejemplos de uso para todas las tareas del plugin AJSoftware'

            doLast {
                println '\n🧱 AJSoftware Scaffolding Plugin - Ejemplos de uso\n'

                project.tasks.findByName('projectHelp')?.execute()
                project.tasks.findByName('moduleHelp')?.execute()
            }
        }
    }

}
