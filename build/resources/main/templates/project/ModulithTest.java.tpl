package ${BASE_PACKAGE};

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

/**
 * Test de verificación de la arquitectura modular con Spring Modulith
 */
class ModulithTest {

    ApplicationModules modules = ApplicationModules.of(Application.class);

    @Test
    void shouldBeCompliant() {
        // Verifica que la estructura de módulos sea válida
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        // Genera documentación de los módulos
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}