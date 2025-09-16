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
        if (packageFromProperty) {
            basePackage = packageFromProperty
        }
        if (!moduleType && typeFromProperty) {
            moduleType = typeFromProperty
        }
        
        // Detectar paquete base del proyecto existente
        if (!packageFromProperty) {
            def appSrcDir = new File(project.rootDir, 'app/src/main/java')
            if (appSrcDir.exists()) {
                def packageDirs = []
                appSrcDir.eachDirRecurse { dir ->
                    if (dir.name.matches('[a-z]+') && new File(dir, 'Application.java').exists()) {
                        def relativePath = appSrcDir.toPath().relativize(dir.toPath()).toString()
                        packageDirs.add(relativePath.replace(File.separator, '.'))
                    }
                }
                if (packageDirs) {
                    basePackage = packageDirs[0]
                }
            }
        }
        
        // Valores por defecto
        basePackage = basePackage ?: 'com.empresa.ecommerce'
        moduleType = moduleType ?: 'default'
        
        if (!moduleName) {
            throw new GradleException("❌ Parámetro requerido: -Pname=nombre-modulo")
        }
        
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.validateModuleName(moduleName)

        // Crear estructura en modules/
        def moduleDir = new File(project.rootDir, "modules/${moduleName}")
        if (moduleDir.exists()) {
            throw new GradleException("⚠ El módulo '${moduleName}' ya existe. Usa otro nombre o elimínalo primero.")
        }

        println "🧱 Generando módulo '${moduleName}' (${moduleType}) en: ${moduleDir}"

        // Nueva estructura hexagonal con subproyectos
        def packagePath = basePackage.replace('.', '/')
        
        // Crear directorios de subproyectos simplificados
        def subprojects = [
            "modules/${moduleName}/domain/model/src/main/java/${packagePath}/model",
            "modules/${moduleName}/domain/model/src/test/java/${packagePath}/model",
            "modules/${moduleName}/domain/usecase/src/main/java/${packagePath}/usecase",
            "modules/${moduleName}/domain/usecase/src/main/java/${packagePath}/usecase/port/in",
            "modules/${moduleName}/domain/usecase/src/main/java/${packagePath}/usecase/port/out",
            "modules/${moduleName}/domain/usecase/src/test/java/${packagePath}/usecase",
            "modules/${moduleName}/infrastructure/driver-adapters/src/main/java/${packagePath}/web",
            "modules/${moduleName}/infrastructure/driver-adapters/src/main/java/${packagePath}/web/dto",
            "modules/${moduleName}/infrastructure/driver-adapters/src/test/java/${packagePath}/web",
            "modules/${moduleName}/infrastructure/driven-adapters/src/main/java/${packagePath}/persistence",
            "modules/${moduleName}/infrastructure/driven-adapters/src/main/java/${packagePath}/persistence/entity",
            "modules/${moduleName}/infrastructure/driven-adapters/src/main/java/${packagePath}/persistence/repository",
            "modules/${moduleName}/infrastructure/driven-adapters/src/test/java/${packagePath}/persistence"
        ]

        subprojects.each { path ->
            new File(project.rootDir, path).mkdirs()
        }

        // Generar build.gradle para cada subproyecto
        def vars = [
            BASE_PACKAGE: basePackage,
            MODULE: moduleName,
            JAVA_VERSION: '17'
        ]
        
        // Build.gradle para domain/model
        def modelsDir = new File(project.rootDir, "modules/${moduleName}/domain/model")
        def modelsBuild = new File(modelsDir, "build.gradle")
        def modelsBuildContent = com.ajsoftware.scaffold.helpers.ModuleFileHelper.generateFromTemplate(
            this.class.classLoader, "module/domain-model-build.gradle.tpl", vars)
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.writeUtf8File(modelsBuild.absolutePath, modelsBuildContent)
        
        // Build.gradle para domain/usecase
        def usecaseDir = new File(project.rootDir, "modules/${moduleName}/domain/usecase")
        def usecaseBuild = new File(usecaseDir, "build.gradle")
        def usecaseBuildContent = com.ajsoftware.scaffold.helpers.ModuleFileHelper.generateFromTemplate(
            this.class.classLoader, "module/domain-usecase-build.gradle.tpl", vars)
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.writeUtf8File(usecaseBuild.absolutePath, usecaseBuildContent)
        
        // Build.gradle para infrastructure/driver-adapters
        def driverDir = new File(project.rootDir, "modules/${moduleName}/infrastructure/driver-adapters")
        def driverBuild = new File(driverDir, "build.gradle")
        def driverBuildContent = com.ajsoftware.scaffold.helpers.ModuleFileHelper.generateFromTemplate(
            this.class.classLoader, "module/infrastructure-driver-adapters-build.gradle.tpl", vars)
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.writeUtf8File(driverBuild.absolutePath, driverBuildContent)
        
        // Build.gradle para infrastructure/driven-adapters
        def drivenDir = new File(project.rootDir, "modules/${moduleName}/infrastructure/driven-adapters")
        def drivenBuild = new File(drivenDir, "build.gradle")
        def drivenBuildContent = com.ajsoftware.scaffold.helpers.ModuleFileHelper.generateFromTemplate(
            this.class.classLoader, "module/infrastructure-driven-adapters-build.gradle.tpl", vars)
        com.ajsoftware.scaffold.helpers.ModuleFileHelper.writeUtf8File(drivenBuild.absolutePath, drivenBuildContent)
        
        // Crear package-info.java para documentar el módulo en la raíz
        def packageInfoDir = new File(project.rootDir, "modules/${moduleName}")
        def packageInfoFile = new File(packageInfoDir, "package-info.java")
        packageInfoFile.text = com.ajsoftware.scaffold.helpers.ModuleFileHelper.createModuleDocumentation(moduleName, basePackage)
        
        // Actualizar settings.gradle para incluir los subproyectos
        def settingsFile = new File(project.rootDir, "settings.gradle")
        if (settingsFile.exists()) {
            def settingsContent = settingsFile.text
            def moduleIncludes = """
// Módulo ${moduleName}
include 'modules:${moduleName}:domain:model'
include 'modules:${moduleName}:domain:usecase'
include 'modules:${moduleName}:infrastructure:driver-adapters'
include 'modules:${moduleName}:infrastructure:driven-adapters'
"""
            
            // Agregar al final del archivo
            settingsFile.text = settingsContent + moduleIncludes
        }

        // En monolito modular no necesitamos build.gradle separados
        // Los módulos son paquetes Java organizados según Spring Modulith

        println """
📦 Módulo '${moduleName}' generado con arquitectura hexagonal:

🏢 DOMAIN:
  • models/        - Entidades y objetos de valor
  • usecase/       - Casos de uso y puertos

🔌 INFRASTRUCTURE:
  • driver-adapters/  - Controladores REST, GraphQL
  • driven-adapters/  - Repositorios, APIs externas

✔ 4 subproyectos con build.gradle independientes
✔ package-info.java creado
✔ Estructura compatible con Spring Modulith
"""
    }

}
