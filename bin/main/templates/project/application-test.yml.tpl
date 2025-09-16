server:
    port: 8081        # Puerto fijo para pruebas (ajustable si se parametriza en el futuro)

spring:
    application:
        name: ${PROJECT_NAME}-test
    main:
        allow-bean-definition-overriding: true  # Útil para mocks y contextos parciales

modulith:
    base-package: ${BASE_PACKAGE}
    modules: []                     # Aquí CreateModuleTask puede añadir módulos de prueba

logging:
    level:
        root: WARN                    # Menos ruido en logs de test
        org.springframework.modulith: DEBUG
        # @module-loggers-test         # Ancla para añadir loggers específicos en pruebas