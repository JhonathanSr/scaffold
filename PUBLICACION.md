# 📦 Guía de Publicación - AJSoftware Scaffolding Plugin

## 🎯 Opción 1: GitHub Packages (Recomendado para equipos)

### 1. Configurar credenciales

```bash
# Variables de entorno
export GITHUB_USERNAME=tu-usuario
export GITHUB_TOKEN=ghp_tu-token-personal

# O en gradle.properties (~/.gradle/gradle.properties)
gpr.user=tu-usuario
gpr.key=ghp_tu-token-personal
```

### 2. Publicar

```bash
./gradlew publish
```

### 3. Uso en proyectos

```gradle
// settings.gradle
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven {
            name = "GitHubPackages"
            url = "https://maven.pkg.github.com/SteverWiw/scaffolding"
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

// build.gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

## 🌍 Opción 2: Gradle Plugin Portal (Público)

### 1. Crear cuenta en https://plugins.gradle.org

### 2. Configurar API keys

```bash
# ~/.gradle/gradle.properties
gradle.publish.key=tu-api-key
gradle.publish.secret=tu-secret
```

### 3. Publicar

```bash
./gradlew publishPlugins
```

### 4. Uso público

```gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

## 🏢 Opción 3: Repositorio Empresarial (Nexus/Artifactory)

### 1. Configurar repositorio

```gradle
// publish.gradle
repositories {
    maven {
        name = "CompanyNexus"
        url = "https://nexus.empresa.com/repository/gradle-plugins/"
        credentials {
            username = System.getenv("NEXUS_USERNAME")
            password = System.getenv("NEXUS_PASSWORD")
        }
    }
}
```

### 2. Uso empresarial

```gradle
// settings.gradle
pluginManagement {
    repositories {
        maven {
            url = "https://nexus.empresa.com/repository/gradle-plugins/"
            credentials {
                username = System.getenv("NEXUS_USERNAME")
                password = System.getenv("NEXUS_PASSWORD")
            }
        }
    }
}

// build.gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

## 🚀 Flujo de trabajo recomendado

### Para desarrollo:
```bash
./gradlew publishToMavenLocal
```

### Para equipos (GitHub Packages):
```bash
./gradlew publish
```

### Para comunidad (Plugin Portal):
```bash
./gradlew publishPlugins
```

## 📋 Checklist antes de publicar

- [ ] Tests pasan: `./gradlew test`
- [ ] Plugin compila: `./gradlew build`
- [ ] Documentación actualizada
- [ ] Versión incrementada en `build.gradle`
- [ ] Credenciales configuradas
- [ ] Repository público en GitHub

## 🔧 Comandos útiles

```bash
# Verificar configuración
./gradlew tasks --group=publishing

# Publicar solo localmente
./gradlew publishToMavenLocal

# Publicar a GitHub Packages
./gradlew publish

# Publicar a Plugin Portal
./gradlew publishPlugins

# Ver información del plugin
./gradlew pluginDescriptors
```