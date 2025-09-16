package com.ajsoftware.scaffold.module

import org.gradle.api.GradleException
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option

import static com.ajsoftware.scaffold.helpers.ModuleFileHelper.*

class GenerateModuleTask extends DefaultTask {

    @Input String moduleName
    @Input @Optional String basePackage = 'com.ajsoftware'
    @Input @Optional String moduleType = 'default'

    @Option(option = 'name', description = 'Nombre del módulo')
    void setName(String name) { this.moduleName = name }

    @Option(option = 'package', description = 'Paquete base')
    void setBasePackage(String pkg) { this.basePackage = pkg }

    @Option(option = 'type', description = 'Tipo de módulo (rest, batch, default)')
    void setModuleType(String type) { this.moduleType = type }

    @TaskAction
    void generate() {
        validateModuleName(moduleName)

        def moduleDir = new File(project.rootDir, "modules/${moduleName}")
        if (moduleDir.exists()) {
            throw new GradleException("⚠ El módulo '${moduleName}' ya existe. Usa otro nombre o elimínalo primero.")
        }

        println "🧱 Generando módulo '${moduleName}' (${moduleType}) en: ${moduleDir}"

        // Estructura base
        def folders = [
            'domain',
            'infrastructure/driven-adapters',
            'infrastructure/entry-points',
            'test'
        ]

        switch (moduleType.toLowerCase()) {
            case 'rest':
                folders += ['application/rest']
                break
            case 'batch':
                folders += ['application/batch']
                break
            case 'default':
                folders += ['application']
                break
            default:
                println "⚠ Tipo de módulo desconocido: '${moduleType}'. Se usará estructura por defecto."
                folders += ['application']
        }

        folders.each { path ->
            new File(moduleDir, path).mkdirs()
        }

        // Variables para plantilla
        def vars = [
            BASE_PACKAGE: basePackage,
            MODULE      : moduleName.toLowerCase(),
            MODULE_CAP  : capitalizeFirst(moduleName)
        ]

        // Generar build.gradle
        def buildGradleFile = new File(moduleDir, 'build.gradle')
        buildGradleFile.text = generateFromTemplate(
            this.class.classLoader,
            'module/build.gradle.tpl',
            vars
        )

        // Actualizar settings.gradle y boot/build.gradle
        registerModuleInSettings(project.rootDir, moduleName)
        registerModuleInBootBuild(project.rootDir, moduleName)

        println '''
📦 Estructura generada:
- domain/
- infrastructure/driven-adapters/
- infrastructure/entry-points/
- application/
- test/
✔ build.gradle generado
✔ settings.gradle actualizado
✔ boot/build.gradle actualizado
'''
    }

}
