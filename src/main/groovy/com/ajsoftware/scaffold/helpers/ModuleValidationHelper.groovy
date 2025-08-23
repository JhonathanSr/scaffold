package com.ajsoftware.scaffold.helpers

class ModuleValidationHelper {

    static void validateModuleName(String name) {
        if (!name || name.trim().isEmpty()) {
            throw new IllegalArgumentException('❌ Debe proveer --name. Ejemplo: gradle generateModule --name=inventory')
        }
    }

}
