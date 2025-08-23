package com.ajsoftware.scaffold.delete

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

/**
 * Task for deleting a module and its references from the project.
 */
class DeleteModuleTask extends DefaultTask {

    @Input
    String moduleName

    @Option(option = 'name', description = 'Nombre del módulo a eliminar')
    void setName(String name) { this.moduleName = name }

    @TaskAction
    void delete() {
        if (!moduleName) {
            throw new IllegalArgumentException('Debe proveer --name para el módulo')
        }

        def rootDir = project.rootDir
        def moduleDir = new File(rootDir, moduleName)

        if (moduleDir.exists()) {
            project.delete(moduleDir)
            println "🗑 Carpeta del módulo '${moduleName}' eliminada"
        } else {
            println "⚠ No se encontró carpeta para '${moduleName}'"
        }

        // settings.gradle
        def settingsFile = new File(rootDir, 'settings.gradle')
        settingsFile.text = settingsFile.text
                .replaceAll("(?m)^include ['\"]${moduleName}['\"]\\s*\n?", '')

        // boot/build.gradle
        def bootBuildFile = new File(rootDir, 'boot/build.gradle')
        bootBuildFile.text = bootBuildFile.text
                .replaceAll("(?m)^\\s*implementation project\\(':${moduleName}'\\)\\s*\n?", '')

        // application.yml
        def appYml = new File(rootDir, 'boot/src/main/resources/application.yml')
        if (appYml.exists()) {
            appYml.text = appYml.text.replaceAll("(?m)^\\s*- ${moduleName}\\s*\n?", '')
        }

        println "✅ Módulo '${moduleName}' y referencias eliminadas"
    }

}
