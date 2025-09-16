package com.ajsoftware.scaffold.module

import org.gradle.api.GradleException
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*
import org.gradle.api.tasks.options.Option

import static com.ajsoftware.scaffold.helpers.ModuleFileHelper.*

class GenerateModuleTask extends DefaultTask {

    @Input @Optional String moduleName
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
        // Obtener valores de propiedades del proyecto
        def moduleNameFromProperty = project.findProperty('module') ?: project.findProperty('name')
        def packageFromProperty = project.findProperty('package')
        def typeFromProperty = project.findProperty('type')
        
        // Usar valores de propiedades si no están configurados
        if (!moduleName && moduleNameFromProperty) {
            moduleName = moduleNameFromProperty
        }
        if (!basePackage && packageFromProperty) {
            basePackage = packageFromProperty
        }
        if (!moduleType && typeFromProperty) {
            moduleType = typeFromProperty
        }
        
        // Valores por defecto
        basePackage = basePackage ?: 'com.ajsoftware'
        moduleType = moduleType ?: 'default'
        
        if (!moduleName) {
            throw new GradleException("❌ Parámetro requerido: -Pname=nombre-modulo")
        }
        
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.validateModuleName(moduleName)

        // Crear estructura en src/main/java en lugar de modules/
        def modulePackagePath = "src/main/java/${basePackage.replace('.', '/')}/${moduleName}"
        def moduleDir = new File(project.rootDir, modulePackagePath)
        if (moduleDir.exists()) {
            throw new GradleException("⚠ El módulo '${moduleName}' ya existe. Usa otro nombre o elimínalo primero.")
        }

        println "🧱 Generando módulo '${moduleName}' (${moduleType}) en: ${moduleDir}"

        // Estructura base compatible con Spring Modulith
        def packagePath = "src/main/java/${basePackage.replace('.', '/')}/${moduleName}"
        def folders = [
            "${packagePath}/domain/model",
            "${packagePath}/domain/port/in",
            "${packagePath}/domain/port/out",
            "${packagePath}/domain/service",
            "${packagePath}/adapter/in/web",
            "${packagePath}/adapter/in/web/dto",
            "${packagePath}/adapter/out/persistence",
            "${packagePath}/adapter/out/persistence/entity",
            "${packagePath}/adapter/out/persistence/repository",
            "src/test/java/${basePackage.replace('.', '/')}/${moduleName}"
        ]

        folders.each { path ->
            new File(project.rootDir, path).mkdirs()
        }

        // Crear package-info.java para documentar el módulo
        def packageInfoDir = new File(project.rootDir, "src/main/java/${basePackage.replace('.', '/')}/${moduleName}")
        def packageInfoFile = new File(packageInfoDir, "package-info.java")
        packageInfoFile.text = com.ajsoftware.scaffold.helpers.ModuleFileHelper.createModuleDocumentation(moduleName, basePackage)

        // Variables para plantilla
        def vars = [
            BASE_PACKAGE: basePackage,
            MODULE      : moduleName.toLowerCase(),
            MODULE_CAP  : capitalizeFirst(moduleName)
        ]

        // En monolito modular no necesitamos build.gradle separados
        // Los módulos son paquetes Java organizados según Spring Modulith

        println """
📦 Módulo '${moduleName}' generado con estructura hexagonal:
- domain/model/ (modelos de dominio)
- domain/port/in/ (casos de uso)
- domain/port/out/ (puertos de salida)
- domain/service/ (implementaciones)
- adapter/in/web/ (controladores REST)
- adapter/out/persistence/ (adaptadores de BD)
✔ package-info.java creado
✔ Estructura compatible con Spring Modulith
"""
    }

}
