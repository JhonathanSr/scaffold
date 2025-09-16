# 🚀 Cómo usar AJSoftware Scaffolding Plugin

## Método 1: Aplicar plugin en proyecto existente

### 1. En tu `build.gradle`:

```gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}

// O usando sintaxis legacy
buildscript {
    repositories {
        mavenLocal() // Si está publicado localmente
        gradlePluginPortal()
        // O tu repositorio personalizado
        maven { url 'https://maven.pkg.github.com/tu-usuario/scaffolding' }
    }
    dependencies {
        classpath 'com.ajsoftware.scaffold:scaffolding:1.0.0'
    }
}

apply plugin: 'com.ajsoftware.scaffold'
```

### 2. Usar comandos:

```bash
# Crear módulos
./gradlew createModule --name=orders

# Agregar componentes
./gradlew addComponent --module=orders --type=usecase --name=CreateOrder
```

## Método 2: Comando global (recomendado)

### 1. Instalar globalmente:

```bash
# Linux/Mac
curl -sSL https://raw.githubusercontent.com/tu-usuario/scaffolding/main/install.sh | bash

# Windows
powershell -Command "iwr https://raw.githubusercontent.com/tu-usuario/scaffolding/main/install.bat -OutFile install.bat; .\install.bat"
```

### 2. Usar desde cualquier directorio:

```bash
# Crear proyecto nuevo
ajscaffold createProject --name=ecommerce --package=com.empresa.ecommerce

# Navegar al proyecto
cd ecommerce

# Crear módulos
ajscaffold createModule --name=products
ajscaffold createModule --name=orders

# Agregar componentes
ajscaffold addComponent --module=products --type=usecase --name=CreateProduct
ajscaffold addComponent --module=products --type=controller --name=Product
```

## Método 3: Proyecto plantilla (estilo Bancolombia)

### 1. Crear repositorio plantilla:

```bash
# Crear proyecto base
ajscaffold createProject --name=template-spring-modulith --package=com.empresa.template

# Agregar módulos ejemplo
ajscaffold createModule --name=sample
ajscaffold addComponent --module=sample --type=usecase --name=ProcessSample
ajscaffold addComponent --module=sample --type=controller --name=Sample

# Subir a GitHub como template
git init
git add .
git commit -m "Initial template"
git remote add origin https://github.com/tu-empresa/template-spring-modulith.git
git push -u origin main
```

### 2. Usar template:

```bash
# Crear proyecto desde template
gh repo create mi-nuevo-proyecto --template tu-empresa/template-spring-modulith --clone

# O usar GitHub UI: "Use this template"
```

## Método 4: Gradle Init Plugin

### 1. Crear init script:

```bash
# ~/.gradle/init.d/ajscaffold.gradle
initscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    dependencies {
        classpath 'com.ajsoftware.scaffold:scaffolding:1.0.0'
    }
}

allprojects {
    apply plugin: com.ajsoftware.scaffold.ScaffoldingPlugin
}
```

### 2. Usar en cualquier proyecto:

```bash
gradle createModule --name=orders
gradle addComponent --module=orders --type=usecase --name=CreateOrder
```

## 🎯 Flujo recomendado (estilo Bancolombia)

```bash
# 1. Instalar herramienta globalmente
curl -sSL https://install.ajsoftware.com/scaffolding | bash

# 2. Crear proyecto desde cualquier directorio
ajscaffold createProject --name=mi-microservicio --package=com.empresa.microservicio

# 3. Navegar y desarrollar
cd mi-microservicio
ajscaffold createModule --name=core
ajscaffold addComponent --module=core --type=usecase --name=ProcessBusiness

# 4. Verificar arquitectura
./gradlew test --tests="*ModulithTest"

# 5. Ejecutar
./gradlew bootRun
```

## 📋 Comandos disponibles

| Comando | Descripción | Ejemplo |
|---------|-------------|---------|
| `createProject` | Crea proyecto Spring Modulith completo | `--name=app --package=com.empresa.app` |
| `createModule` | Crea módulo con arquitectura hexagonal | `--name=orders` |
| `addComponent` | Agrega componente a módulo | `--module=orders --type=usecase --name=Create` |
| `scaffoldHelp` | Ayuda completa | |

## 🔧 Personalización

### Variables de entorno:

```bash
export AJSCAFFOLD_DEFAULT_PACKAGE=com.miempresa
export AJSCAFFOLD_DEFAULT_JAVA_VERSION=17
export AJSCAFFOLD_DEFAULT_SPRING_VERSION=3.2.0
```

### Configuración local (`.ajscaffold.yml`):

```yaml
defaults:
  package: com.miempresa
  javaVersion: 17
  springBootVersion: 3.2.0
  author: Mi Equipo
templates:
  custom: ~/.ajscaffold/templates
```