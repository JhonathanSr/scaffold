package ${BASE_PACKAGE}.${MODULE};

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SampleModuleTest {

    @Test
    void shouldMockDependency() {
        // Ejemplo: crear un mock y verificar interacción
        Runnable dependency = Mockito.mock(Runnable.class);

        dependency.run();

        verify(dependency, times(1)).run();
        assertNotNull(dependency);
    }
}