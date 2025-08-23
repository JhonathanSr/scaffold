package com.ajsoftware.scaffold.helpers

import java.util.regex.Pattern
import java.util.regex.Matcher

/**
 * Helper class for file operations related to modules, such as creating files with directories,
 * capitalizing strings, and generating files from templates.
 */
class ModuleFileHelper {

    /**
     * Genera una ruta de archivo virtual en baseDir/relativePath asegurando que la ruta existe.
     * En contexto EJB, retorna la ruta como String y no accede al sistema de archivos.
     */
    static String makeFilePath(String baseDir, String relativePath) {
        return "${baseDir}/${relativePath}"
    }

    /**
     * Capitaliza la primera letra de un String.
     */
    static String capitalizeFirst(String str) {
        if (!str) return str
        str.substring(0, 1).toUpperCase() + str.substring(1)
    }

    /**
     * Copia una plantilla .tpl desde resources/groovy y reemplaza variables.
     * Retorna el contenido generado como String, sin escribir en disco.
     *
     * @param loader ClassLoader desde donde buscar el recurso
     * @param templatePath Ruta relativa dentro de com/ajsoftware/scaffold/templates
     * @param vars Mapa de variables para reemplazar en ${...}
     * @return String contenido generado
     */
    static String generateFromTemplate(ClassLoader loader, String templatePath, Map<String, String> vars) {
        def resourcePath = "templates/${templatePath}"
        def stream = loader.getResourceAsStream(resourcePath)
        if (!stream) {
            throw new FileNotFoundException("No se encontró la plantilla: ${templatePath} en ${resourcePath}")
        }

        def text = stream.getText('UTF-8') // ← codificación segura

        vars.each { k, v ->
            def placeholder = "\${${k}}"
            text = text.replaceAll(Pattern.quote(placeholder), Matcher.quoteReplacement(v))
        }

        return text
    }

    /**
     * Escribe contenido en disco con codificación UTF-8.
     *
     * @param path Ruta absoluta o relativa del archivo
     * @param content Contenido a escribir
     */
    static void writeUtf8File(String path, String content) {
        new File(path).setText(content, 'UTF-8')
    }

        /**
     * Registra el módulo en settings.gradle si no está incluido.
     *
     * @param rootDir Directorio raíz del proyecto
     * @param moduleName Nombre del módulo a incluir
     */
    static void registerModuleInSettings(File rootDir, String moduleName) {
        def settingsFile = new File(rootDir, 'settings.gradle')
        def includeLine = "include 'modules/${moduleName}'"
        if (!settingsFile.text.contains(includeLine)) {
            settingsFile.append("\n${includeLine}")
        }
    }

    /**
     * Registra el módulo como dependencia en boot/build.gradle si no está presente.
     *
     * @param rootDir Directorio raíz del proyecto
     * @param moduleName Nombre del módulo a agregar como dependencia
     */
    static void registerModuleInBootBuild(File rootDir, String moduleName) {
        def bootBuildFile = new File(rootDir, 'boot/build.gradle')
        def dependencyLine = "implementation project(':modules/${moduleName}')"
        if (!bootBuildFile.text.contains(dependencyLine)) {
            def depsBlock = bootBuildFile.text.find(/dependencies \{[\s\S]*?\}/)
            if (depsBlock) {
                def newDepsBlock = depsBlock.replace(
                    'dependencies {',
                    "dependencies {\n    ${dependencyLine}"
                )
                bootBuildFile.text = bootBuildFile.text.replace(depsBlock, newDepsBlock)
            } else {
                bootBuildFile.append("\ndependencies {\n    ${dependencyLine}\n}")
            }
        }
    }

}
