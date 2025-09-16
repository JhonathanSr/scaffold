package com.ajsoftware.scaffold.project

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import com.ajsoftware.scaffold.helpers.ModuleFileHelper

class CreateProjectTask extends DefaultTask {

    @TaskAction
    void generate() {
        def basePackage = project.findProperty('package') ?: 'com.ajsoftware.demo'
        def projectName = project.findProperty('name') ?: 'demo'
        def javaVersion = project.findProperty('javaVersion') ?: '17'
        def springBootVer = project.findProperty('springBootVersion') ?: '3.5.0'
        def serverPort = project.findProperty('serverPort') ?: '8080'
        def modulithVersion = project.findProperty('modulithVersion') ?: '1.1.0'
        def year = Calendar.getInstance().get(Calendar.YEAR).toString()

        def baseDir = project.projectDir
        def srcMain = new File(baseDir, "src/main/java/${basePackage.replace('.', '/')}")
        def srcTest = new File(baseDir, "src/test/java/${basePackage.replace('.', '/')}")

        srcMain.mkdirs()
        srcTest.mkdirs()

        def binding = [
                BASE_PACKAGE       : basePackage,
                BASE_PACKAGE_PATH  : basePackage.replace('.', '/'),
                PROJECT_NAME       : projectName,
                JAVA_VERSION       : javaVersion,
                SPRING_BOOT_VERSION: springBootVer,
                SERVER_PORT        : serverPort,
                YEAR               : year,
                MODULITH_VERSION   : modulithVersion
        ]

        def templates = [
                'build.gradle.tpl'         : new File(baseDir, 'build.gradle'),
                'settings.gradle.tpl'      : new File(baseDir, 'settings.gradle'),
                'README.md.tpl'            : new File(baseDir, 'README.md'),
                'HELP.md.tpl'              : new File(baseDir, 'HELP.md'),
                '.gitignore.tpl'           : new File(baseDir, '.gitignore'),
                '.editorconfig.tpl'        : new File(baseDir, '.editorconfig'),
                'application.yml.tpl'      : new File(baseDir, 'src/main/resources/application.yml'),
                'application-test.yml.tpl' : new File(baseDir, 'src/test/resources/application-test.yml'),
                'log4j2.properties.tpl'    : new File(baseDir, 'src/main/resources/log4j2.properties'),
                'banner.txt.tpl'           : new File(baseDir, 'src/main/resources/banner.txt'),
                'Application.java.tpl'     : new File(srcMain, 'Application.java'),
                'StatusController.java.tpl': new File(srcMain, 'StatusController.java'),
                'ApplicationTests.java.tpl': new File(srcTest, 'ApplicationTests.java'),
                'ModulithTest.java.tpl'    : new File(srcTest, 'ModulithTest.java')
        ]

        templates.each { tplName, outputFile ->
            def resourcePath = "templates/project/${tplName}"
            def resource = this.class.classLoader.getResource(resourcePath)
            if (!resource) throw new FileNotFoundException("❌ No se encontró la plantilla: ${resourcePath}")
            def content = ModuleFileHelper.generateFromTemplate(this.class.classLoader, "project/${tplName}", binding)
            outputFile.parentFile.mkdirs()
            ModuleFileHelper.writeUtf8File(outputFile.absolutePath, content)
        }

        // Crear carpeta config para configuración global
        new File(srcMain, 'config').mkdirs()
        
        // Crear archivo de configuración global
        def configFile = new File(srcMain, 'config/GlobalConfig.java')
        configFile.text = """package ${basePackage}.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuración global de la aplicación
 * Aquí van las configuraciones que afectan a toda la aplicación
 */
@Configuration
public class GlobalConfig {
    // Configuraciones globales
}
"""

        println "✅ Proyecto Spring Modulith generado en ${baseDir.absolutePath}"
        println "📦 Paquete base: ${basePackage}"
        println "🏠 Estructura de monolito modular lista"
        println '📝 Archivos generados: README.md, settings.gradle, build.gradle'
        println '🚀 Usa: ./gradlew createModule --name=orders para crear módulos'
    }

}
