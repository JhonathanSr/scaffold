# ${PROJECT_NAME}

Proyecto base generado con el scaffold de AJ SOFTWARE usando Spring Boot ${SPRING_BOOT_VERSION} + Spring Modulith ${MODULITH_VERSION}.

## 🚀 Propósito
- Servir como punto de entrada del monolito modular.
- Orquestar módulos generados por `createModule`.
- Centralizar configuración global (`application.yml`, logging, seguridad).
- Mantener consistencia y escalabilidad a largo plazo.

## 📦 Requisitos
- Java ${JAVA_VERSION}+
- Gradle 8+
- Spring Boot ${SPRING_BOOT_VERSION}
- Spring Modulith ${MODULITH_VERSION}

## ▶ Ejecución
```bash
./gradlew bootRun
# Windows: .\gradlew.bat bootRun


- URL de salud: http://localhost:{SERVER_PORT}/actuator/health](http://localhost:{SERVER_PORT}/actuator/health)
🧩 Módulos
<!-- @modules-list -->
(Los módulos registrados se listarán aquí automáticamente)
🛠 Cómo añadir un módulo
./gradlew createModule -PmoduleName=nombre-del-modulo


Esto generará el módulo y lo añadirá a settings.gradle y a esta lista.
📄 Licencia
© ${YEAR} AJ SOFTWARE — Todos los derechos reservados.
