# ⚡ Comandos Rápidos - Publicación

## 🚀 Publicar en Plugin Portal

```bash
# 1. Validar plugin
call gradlew.bat validatePlugins

# 2. Publicar
call gradlew.bat publishPlugins
```

## 📝 Configuración requerida

**Archivo**: `%USERPROFILE%\.gradle\gradle.properties`
```properties
gradle.publish.key=TU_API_KEY
gradle.publish.secret=TU_SECRET
```

## 🎯 Uso después de publicación

```gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

## 📋 Comandos del plugin

```bash
# Crear proyecto
gradle createProject -Pname=miapp -Ppackage=com.empresa.miapp

# Si hay espacios en la ruta, usar comillas
gradle createProject "-Pname=miapp" "-Ppackage=com.empresa.miapp"

# Crear módulo
gradle createModule -Pmodule=products

# Agregar componentes
gradle addComponent -Pmodule=products -Ptype=usecase -Pname=CreateProduct
gradle addComponent -Pmodule=products -Ptype=controller -Pname=Product
gradle addComponent -Pmodule=products -Ptype=repository -Pname=Product
```

## ⚠️ **Importante:**
- **Evitar espacios** en nombres de directorios
- **Usar comillas** si hay espacios en la ruta
- **Usar guiones** en lugar de espacios: `mi-app` no `mi app`

## 🔗 Enlaces útiles

- **Plugin Portal**: https://plugins.gradle.org
- **API Keys**: https://plugins.gradle.org (Profile → API Keys)
- **Documentación**: https://docs.gradle.org/current/userguide/publishing_gradle_plugins.html