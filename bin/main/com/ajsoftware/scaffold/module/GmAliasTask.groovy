package com.ajsoftware.scaffold.module

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class GmAliasTask extends DefaultTask {

    @TaskAction
    void run() {
        println '''
╔════════════════════════════════════════════════╗
║   🧱 AJSoftware Module Generator (Alias: gm)   ║
╚════════════════════════════════════════════════╝

Ejemplo de uso:
gradle gm --name=inventory
'''
        // No ejecuta directamente, solo muestra ayuda
        println '➡ Ejecutando generateModule con parámetros proporcionados...'
    }

}
