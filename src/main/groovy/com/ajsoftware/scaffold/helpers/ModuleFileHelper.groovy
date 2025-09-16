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
     * Valida que el nombre del módulo sea válido para Spring Modulith
     */
    static void validateModuleName(String moduleName) {
        if (!moduleName) {
            throw new IllegalArgumentException("❌ El nombre del módulo no puede estar vacío")
        }
        if (!moduleName.matches(/^[a-z][a-z0-9]*$/)) {
            throw new IllegalArgumentException("❌ El nombre del módulo debe ser lowercase y solo contener letras y números: ${moduleName}")
        }
    }

    /**
     * Crea un comentario de documentación para package-info.java
     */
    static String createModuleDocumentation(String moduleName, String basePackage) {
        return """/**
 * Módulo ${capitalizeFirst(moduleName)} - Arquitectura Hexagonal
 * 
 * Este módulo sigue los principios de arquitectura hexagonal y Spring Modulith:
 * - domain/model/: Modelos de dominio ricos
 * - domain/port/in/: Puertos de entrada (casos de uso)
 * - domain/port/out/: Puertos de salida (repositorios, servicios externos)
 * - domain/service/: Implementaciones de casos de uso
 * - adapter/in/: Adaptadores de entrada (controladores, listeners)
 * - adapter/out/: Adaptadores de salida (persistencia, servicios externos)
 * 
 * @author Scaffolding Plugin
 */
package ${basePackage}.${moduleName};
"""
    }

}
