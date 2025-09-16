package com.ajsoftware.scaffold.component

import org.gradle.api.Plugin
import org.gradle.api.Project

class ComponentPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.tasks.register('addComponent', AddComponentTask) {
            group = 'Scaffolding'
            description = 'Agrega un componente (usecase, controller, repository, model, adapter) a un módulo existente'
        }

        project.tasks.register('componentHelp') {
            group = 'Scaffolding'
            description = 'Muestra ejemplos de uso para agregar componentes'

            doLast {
                println '''
🧩 Ejemplos de uso - Agregar Componentes:

./gradlew addComponent -Pmodule=orders -Ptype=usecase -Pname=CreateOrder
./gradlew addComponent -Pmodule=orders -Ptype=controller -Pname=Order
./gradlew addComponent -Pmodule=inventory -Ptype=model -Pname=Product
./gradlew addComponent -Pmodule=customers -Ptype=repository -Pname=Customer
./gradlew addComponent -Pmodule=orders -Ptype=adapter -Pname=OrderNotification

Tipos soportados: usecase, controller, repository, model, adapter
'''
            }
        }
    }
}