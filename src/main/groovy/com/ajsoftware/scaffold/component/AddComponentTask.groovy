package com.ajsoftware.scaffold.component

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

import static com.ajsoftware.scaffold.helpers.ModuleFileHelper.*

class AddComponentTask extends DefaultTask {

    @Input @Optional String moduleName
    @Input @Optional String componentType
    @Input @Optional String componentName
    @Input @Optional String basePackage = 'com.ajsoftware'

    @Option(option = 'module', description = 'Nombre del módulo donde agregar el componente')
    void setModuleName(String module) { this.moduleName = module }

    @Option(option = 'type', description = 'Tipo de componente (usecase, controller, repository, model, adapter)')
    void setComponentType(String type) { this.componentType = type }

    @Option(option = 'name', description = 'Nombre del componente')
    void setComponentName(String name) { this.componentName = name }

    @Option(option = 'package', description = 'Paquete base')
    void setBasePackage(String pkg) { this.basePackage = pkg }

    @TaskAction
    void generate() {
        // Obtener valores de propiedades del proyecto
        def moduleFromProperty = project.findProperty('module')
        def typeFromProperty = project.findProperty('type')
        def nameFromProperty = project.findProperty('name')
        def packageFromProperty = project.findProperty('package')
        
        // Usar valores de propiedades si no están configurados
        if (!moduleName && moduleFromProperty) moduleName = moduleFromProperty
        if (!componentType && typeFromProperty) componentType = typeFromProperty
        if (!componentName && nameFromProperty) componentName = nameFromProperty
        if (packageFromProperty) {
            basePackage = packageFromProperty
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
        
        basePackage = basePackage ?: 'com.empresa.ecommerce'
        
        if (!moduleName || !componentType || !componentName) {
            throw new GradleException("❌ Parámetros requeridos: -Pmodule=nombre -Ptype=tipo -Pname=nombre")
        }

        def moduleDir = new File(project.rootDir, "modules/${moduleName}")
        if (!moduleDir.exists()) {
            throw new GradleException("❌ El módulo '${moduleName}' no existe. Créalo primero con createModule.")
        }

        def vars = [
            BASE_PACKAGE: basePackage,
            MODULE: moduleName.toLowerCase(),
            MODULE_CAP: capitalizeFirst(moduleName),
            COMPONENT: componentName,
            COMPONENT_CAP: capitalizeFirst(componentName)
        ]

        switch (componentType.toLowerCase()) {
            case 'usecase':
                generateUseCase(moduleDir, vars)
                break
            case 'controller':
                generateController(moduleDir, vars)
                break
            case 'repository':
                generateRepository(moduleDir, vars)
                break
            case 'model':
                generateModel(moduleDir, vars)
                break
            case 'adapter':
                generateAdapter(moduleDir, vars)
                break
            default:
                throw new GradleException("❌ Tipo de componente no soportado: ${componentType}")
        }

        println "✅ Componente ${componentType} '${componentName}' agregado al módulo '${moduleName}'"
    }

    private void generateUseCase(File moduleDir, Map vars) {
        def useCaseDir = new File(moduleDir, "domain/port/in")
        def useCaseImplDir = new File(moduleDir, "domain/service")
        useCaseDir.mkdirs()
        useCaseImplDir.mkdirs()

        def useCaseFile = new File(useCaseDir, "${vars.COMPONENT_CAP}UseCase.java")
        def useCaseContent = generateFromTemplate(
            this.class.classLoader,
            'domain/UseCase.java.tpl',
            vars
        )
        writeUtf8File(useCaseFile.absolutePath, useCaseContent)

        def implFile = new File(useCaseImplDir, "${vars.COMPONENT_CAP}Service.java")
        def implContent = generateFromTemplate(
            this.class.classLoader,
            'domain/UseCaseImpl.java.tpl',
            vars
        )
        writeUtf8File(implFile.absolutePath, implContent)
    }

    private void generateController(File moduleDir, Map vars) {
        def controllerDir = new File(moduleDir, "adapter/in/web")
        def dtoDir = new File(controllerDir, "dto")
        controllerDir.mkdirs()
        dtoDir.mkdirs()

        def controllerFile = new File(controllerDir, "${vars.COMPONENT_CAP}Controller.java")
        def controllerContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/api/Controller.java.tpl',
            vars
        )
        writeUtf8File(controllerFile.absolutePath, controllerContent)

        def requestFile = new File(dtoDir, "${vars.COMPONENT_CAP}Request.java")
        def responseFile = new File(dtoDir, "${vars.COMPONENT_CAP}Response.java")
        
        def requestContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/api/dto/CreateRequest.java.tpl',
            vars
        )
        def responseContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/api/dto/Response.java.tpl',
            vars
        )
        
        writeUtf8File(requestFile.absolutePath, requestContent)
        writeUtf8File(responseFile.absolutePath, responseContent)
    }

    private void generateRepository(File moduleDir, Map vars) {
        def portDir = new File(moduleDir, "domain/port/out")
        def adapterDir = new File(moduleDir, "adapter/out/persistence")
        def entityDir = new File(adapterDir, "entity")
        def repoDir = new File(adapterDir, "repository")
        
        [portDir, adapterDir, entityDir, repoDir].each { it.mkdirs() }

        def portFile = new File(portDir, "${vars.COMPONENT_CAP}RepositoryPort.java")
        def portContent = generateFromTemplate(
            this.class.classLoader,
            'domain/Repository.java.tpl',
            vars
        )
        writeUtf8File(portFile.absolutePath, portContent)

        def adapterFile = new File(adapterDir, "${vars.COMPONENT_CAP}PersistenceAdapter.java")
        def adapterContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/adapter/JpaAdapter.java.tpl',
            vars
        )
        writeUtf8File(adapterFile.absolutePath, adapterContent)

        def entityFile = new File(entityDir, "${vars.COMPONENT_CAP}Entity.java")
        def entityContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/entity/Entity.java.tpl',
            vars
        )
        writeUtf8File(entityFile.absolutePath, entityContent)

        def repoFile = new File(repoDir, "${vars.COMPONENT_CAP}JpaRepository.java")
        def repoContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/repository/JpaRepository.java.tpl',
            vars
        )
        writeUtf8File(repoFile.absolutePath, repoContent)
    }

    private void generateModel(File moduleDir, Map vars) {
        def modelDir = new File(moduleDir, "domain/model")
        modelDir.mkdirs()

        def modelFile = new File(modelDir, "${vars.COMPONENT_CAP}.java")
        def modelContent = generateFromTemplate(
            this.class.classLoader,
            'domain/Model.java.tpl',
            vars
        )
        writeUtf8File(modelFile.absolutePath, modelContent)
    }

    private void generateAdapter(File moduleDir, Map vars) {
        def adapterDir = new File(moduleDir, "adapter/out")
        adapterDir.mkdirs()

        def adapterFile = new File(adapterDir, "${vars.COMPONENT_CAP}Adapter.java")
        def adapterContent = generateFromTemplate(
            this.class.classLoader,
            'infrastructure/adapter/JpaAdapter.java.tpl',
            vars
        )
        writeUtf8File(adapterFile.absolutePath, adapterContent)
    }
}