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

./gradlew addComponent --module=orders --type=usecase --name=CreateOrder
./gradlew addComponent --module=orders --type=controller --name=Order
./gradlew addComponent --module=inventory --type=model --name=Product
./gradlew addComponent --module=customers --type=repository --name=Customer
./gradlew addComponent --module=orders --type=adapter --name=OrderNotification

Tipos soportados: usecase, controller, repository, model, adapter
'''
            }
        }
    }
}