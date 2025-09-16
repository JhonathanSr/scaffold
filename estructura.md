# Estructura de Proyecto con Gradle y Arquitectura Hexagonal

```
ctr-backend/
├── build.gradle                        // Archivo principal de Gradle con dependencias 
├── settings.gradle                     // Define el nombre del proyecto para Gradle
├── gradlew                                  // Script del Gradle Wrapper para Linux/Mac
├── gradlew.bat                              // Script del Gradle Wrapper para Windows
└── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
└── src/
    ├── main/
    │   ├── java/
    │   │   └── co/
    │   │       └── gov/
    │   │           └── cvc/
    │   │               └── tasaretri/
    │   │                   ├── Application.java             // Clase principal de Spring Boot, anótala con @ApplicationModule
    │   │                   │
    │   │                   ├── config/                      // Configuración global (ej. SecurityConfig global)
    │   │                   │   └── GlobalSecurityConfig.java
    │   │                   │
    │   │                   ├── declaracion/                 // === MÓDULO DE NEGOCIO: DECLARACIONES ===
    │   │                   │   ├── package-info.java        // Documenta el módulo para Modulith
    │   │                   │   │
    │   │                   │   ├── domain/                  // --- EL HEXÁGONO (Núcleo del Módulo) ---
    │   │                   │   │   ├── model/               // Modelos de dominio ricos, sin anotaciones de framework
    │   │                   │   │   │   ├── Autodeclaracion.java
    │   │                   │   │   │   └── EstadoDeclaracion.java
    │   │                   │   │   │
    │   │                   │   │   ├── port/                // Puertos (Interfaces que definen los contratos)
    │   │                   │   │   │   ├── in/              // Puertos de entrada (lo que la aplicación OFRECE)
    │   │                   │   │   │   │   ├── CrearAutodeclaracionUseCase.java
    │   │                   │   │   │   │   └── PresentarAutodeclaracionUseCase.java
    │   │                   │   │   │   │
    │   │                   │   │   │   └── out/             // Puertos de salida (lo que la aplicación NECESITA)
    │   │                   │   │   │       ├── AutodeclaracionRepositoryPort.java
    │   │                   │   │   │       └── PublicadorEventosDeclaracionPort.java
    │   │                   │   │   │
    │   │                   │   │   └── service/             // Casos de uso (Implementación de los puertos de entrada)
    │   │                   │   │       ├── AutodeclaracionService.java
    │   │                   │   │       └── ...
    │   │                   │   │
    │   │                   │   └── adapter/                 // --- ADAPTADORES (Infraestructura) ---
    │   │                   │       ├── in/                  // Adaptadores de entrada (los que INVOCAN los casos de uso)
    │   │                   │       │   ├── web/             // Adaptador para peticiones HTTP
    │   │                   │       │   │   ├── AutodeclaracionController.java
    │   │                   │       │   │   └── dto/
    │   │                   │       │   │       ├── AutodeclaracionRequest.java  (puede ser un Record)
    │   │                   │       │   │       └── AutodeclaracionResponse.java (puede ser un Record)
    │   │                   │       │   │
    │   │                   │       │   └── message/         // (Opcional) Adaptador para colas de mensajes
    │   │                   │       │       └── DeclaracionMessageListener.java
    │   │                   │       │
    │   │                   │       └── out/                 // Adaptadores de salida (la IMPLEMENTACIÓN de los puertos de salida)
    │   │                   │           ├── persistence/     // Adaptador para la base de datos
    │   │                   │           │   ├── AutodeclaracionPersistenceAdapter.java
    │   │                   │           │   ├── entity/
    │   │                   │           │   │   └── AutodeclaracionEntity.java // Entidad JPA
    │   │                   │           │   ├── mapper/
    │   │                   │           │   │   └── AutodeclaracionMapper.java // Mapea entre Entidad y Modelo de Dominio
    │   │                   │           │   └── repository/
    │   │                   │           │       └── AutodeclaracionJpaRepository.java // Interfaz de Spring Data
    │   │                   │           │
    │   │                   │           └── event/           // Adaptador para publicar eventos entre módulos
    │   │                   │               └── PublicadorEventosDeclaracionAdapter.java
    │   │                   │
    │   │                   ├── seguridad/                   // === MÓDULO DE NEGOCIO: SEGURIDAD ===
    │   │                   │   └── ... (sigue la misma estructura hexagonal interna)
    │   │                   │
    │   │                   ├── auditoria/                   // === MÓDULO DE NEGOCIO: AUDITORÍA ===
    │   │                   │   └── ... (sigue la misma estructura hexagonal interna)
    │   │                   │
    │   │                   ├── parametrizacion/             // === MÓDULO DE NEGOCIO: PARAMETRIZACIÓN ===
    │   │                   │   └── ... (sigue la misma estructura hexagonal interna)
    │   │                   │
    │   │                   ├── contrato/                    // === MÓDULO DE NEGOCIO: CONTRATOS ===
    │   │                   │   └── ... (sigue la misma estructura hexagonal interna)
    │   │                   │
    │   │                   └── reporte/                     // === MÓDULO DE NEGOCIO: REPORTES ===
    │   │                       └── ... (sigue la misma estructura hexagonal interna)
    │   │
    │   └── resources/
    │       ├── application.properties
    │       └── db/
    │           └── migration/                           // Scripts de Flyway o Liquibase para la BBDD
    │               └── V1__initial_schema.sql
    │
    └── test/
        ├── java/
        │   └── co/
        │       └── gov/
        │           └── cvc/
        │               └── tasaretri/
        │                   ├── Modulith.test              // Test de Modulith para verificar la arquitectura modular
        │                   │
        │                   └── declaracion/
        │                       ├── domain/
        │                       │   └── model/
        │                       │       └── AutodeclaracionTest.java // Test unitario del modelo
        │                       │
        │                       ├── service/
        │                       │   └── AutodeclaracionServiceTest.java // Test del caso de uso
        │                       │
        │                       └── adapter/
        │                           ├── in/
        │                           │   └── web/
        │                           │       └── AutodeclaracionControllerTest.java // Test de integración web
        │                           └── out/
        │                               └── persistence/
        │                                   └── AutodeclaracionPersistenceAdapterTest.java // Test de integración de persistencia
        │
        └── resources/
            └── application-test.properties
```
