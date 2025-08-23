# 🌐 Configuración común para todos los perfiles
server:
    port: ${SERVER_PORT}                # Puerto dinámico definido en el binding

spring:
    application:
        name: ${PROJECT_NAME}             # Nombre del proyecto legible
    main:
        allow-bean-definition-overriding: true  # Opcional: útil para pruebas modulares
    profiles:
        active: dev                       # Puede ser sobreescrito con -Dspring.profiles.active=prod

modulith:
    base-package: ${BASE_PACKAGE}       # Paquete raíz para escaneo modular
    modules: []                         # Lista extensible por CreateModuleTask

management:
    endpoints:
        web:
        exposure:
            include: "*"                  # Exposición total de endpoints actuator
        endpoint:
            health:
                show-details: always         # Muestra detalles en /actuator/health

logging:
    level:
        root: INFO
        org.springframework.modulith: DEBUG
        # @module-loggers                  ← Ancla opcional para añadir loggers por módulo

---

# 🧪 Perfil de desarrollo
spring:
    config:
        activate:
            on-profile: dev

logging:
    level:
        root: DEBUG
        com.ajsoftware: DEBUG

scaffold:
    enabled: true
    banner: "🛠️ Modo desarrollo activo"
    auto-inject: true

modulith:
    dev-mode: true

---

# 🧪 Perfil de pruebas
spring:
    config:
        activate:
            on-profile: test

logging:
    level:
        root: WARN

scaffold:
    enabled: false

modulith:
    dev-mode: false

---

# 🚀 Perfil de producción
spring:
    config:
        activate:
            on-profile: prod

logging:
    level:
        root: INFO

scaffold:
    enabled: false

modulith:
    dev-mode: false