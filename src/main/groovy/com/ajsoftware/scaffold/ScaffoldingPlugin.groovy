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
        project.pluginManager.apply('com.ajsoftware.scaffold.component')
        
        // Registrar tarea createModule desde el plugin de módulos
        project.tasks.register('createModule', com.ajsoftware.scaffold.module.GenerateModuleTask) {
            group = 'Scaffolding'
            description = 'Crea un nuevo módulo con estructura hexagonal'
        }

        project.tasks.register('scaffoldHelp') {
            group = 'Scaffolding'
            description = 'Muestra ejemplos de uso para todas las tareas del plugin AJSoftware'

            doLast {
                println '\n🧱 AJSoftware Scaffolding Plugin - Ejemplos de uso\n'

                def projectHelpTask = project.tasks.findByName('projectHelp')
                def moduleHelpTask = project.tasks.findByName('moduleHelp')
                def componentHelpTask = project.tasks.findByName('componentHelp')
                
                if (projectHelpTask) projectHelpTask.actions.each { it.execute(projectHelpTask) }
                if (moduleHelpTask) moduleHelpTask.actions.each { it.execute(moduleHelpTask) }
                if (componentHelpTask) componentHelpTask.actions.each { it.execute(componentHelpTask) }
            }
        }
    }

}
