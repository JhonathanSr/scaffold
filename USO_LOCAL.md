# 🏠 Guía de Uso Local - AJSoftware Scaffolding Plugin

## 🎯 Plugin disponible en dos modalidades

### 📦 **Maven Local** (Disponible AHORA)
- ✅ Uso inmediato sin esperar aprobación
- ✅ Ideal para desarrollo y pruebas
- ✅ Control total sobre versiones

### 🌍 **Gradle Plugin Portal** (En aprobación)
- ⏳ Esperando aprobación de Gradle
- 🚀 Uso público global una vez aprobado
- 📧 Notificación por email

---

## 🚀 Uso desde Maven Local

### **1. Configuración del proyecto**

**build.gradle**
```gradle
buildscript {
    repositories {
        mavenLocal()          // ← Busca primero en local
        gradlePluginPortal()  // ← Fallback para otras dependencias
    }
    dependencies {
        classpath 'io.github.steverwi:scaffolding:1.0.0'
    }
}

apply plugin: 'io.github.steverwi.scaffold'
```

**settings.gradle**
```gradle
rootProject.name = 'mi-proyecto'
```

### **2. Comandos disponibles**

```bash
# Crear proyecto Spring Modulith completo
./gradlew createProject -Pname=ecommerce -Ppackage=com.empresa.ecommerce

# Crear módulos con arquitectura hexagonal
./gradlew createModule -Pmodule=products
./gradlew createModule -Pmodule=orders
./gradlew createModule -Pmodule=customers

# Agregar componentes
./gradlew addComponent -Pmodule=products -Ptype=usecase -Pname=CreateProduct
./gradlew addComponent -Pmodule=products -Ptype=controller -Pname=Product
./gradlew addComponent -Pmodule=products -Ptype=repository -Pname=Product
./gradlew addComponent -Pmodule=products -Ptype=model -Pname=Product

# Ver ayuda
./gradlew scaffoldHelp
./gradlew componentHelp
```

---

## 📁 Ejemplo completo paso a paso

### **Paso 1: Crear directorio del proyecto**
```bash
mkdir mi-ecommerce
cd mi-ecommerce
```

### **Paso 2: Crear archivos de configuración**

**build.gradle**
```gradle
buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    dependencies {
        classpath 'io.github.steverwi:scaffolding:1.0.0'
    }
}

apply plugin: 'io.github.steverwi.scaffold'
```

**settings.gradle**
```gradle
rootProject.name = 'ecommerce'
```

### **Paso 3: Generar estructura completa**
```bash
# Proyecto base
./gradlew createProject -Pname=ecommerce -Ppackage=com.empresa.ecommerce

# Módulos de negocio
./gradlew createModule -Pmodule=products
./gradlew createModule -Pmodule=orders
./gradlew createModule -Pmodule=customers

# Casos de uso
./gradlew addComponent -Pmodule=products -Ptype=usecase -Pname=CreateProduct
./gradlew addComponent -Pmodule=products -Ptype=usecase -Pname=FindProduct
./gradlew addComponent -Pmodule=orders -Ptype=usecase -Pname=CreateOrder

# Controladores REST
./gradlew addComponent -Pmodule=products -Ptype=controller -Pname=Product
./gradlew addComponent -Pmodule=orders -Ptype=controller -Pname=Order

# Repositorios
./gradlew addComponent -Pmodule=products -Ptype=repository -Pname=Product
./gradlew addComponent -Pmodule=orders -Ptype=repository -Pname=Order
```

---

## 🏗️ Estructura generada

```
ecommerce/
├── src/main/java/com/empresa/ecommerce/
│   ├── products/
│   │   ├── domain/
│   │   │   ├── model/
│   │   │   ├── port/in/     # CreateProductUseCase, FindProductUseCase
│   │   │   ├── port/out/    # ProductRepositoryPort
│   │   │   └── service/     # ProductService
│   │   ├── adapter/
│   │   │   ├── in/web/      # ProductController
│   │   │   └── out/persistence/ # ProductPersistenceAdapter
│   │   └── package-info.java
│   ├── orders/
│   │   └── ... (misma estructura)
│   ├── customers/
│   │   └── ... (misma estructura)
│   ├── Application.java
│   └── StatusController.java
├── src/test/java/
│   └── ModulithTest.java    # Tests de arquitectura
├── build.gradle
├── settings.gradle
└── README.md
```

---

## 🔄 Actualizar el plugin

Si haces cambios al plugin:

```bash
# 1. En directorio del plugin
cd scaffolding
./gradlew publishToMavenLocal

# 2. En tu proyecto
./gradlew --refresh-dependencies
```

---

## 🌟 Ventajas del uso local

✅ **Disponible inmediatamente** - No esperar aprobación
✅ **Sin configuración compleja** - Solo mavenLocal()
✅ **Desarrollo iterativo** - Cambios inmediatos
✅ **Sin internet** - Funciona offline
✅ **Control de versiones** - Usa la versión que necesites

---

## 🚀 Migración futura

Cuando el plugin sea aprobado en Gradle Plugin Portal, solo cambiar:

**De:**
```gradle
buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath 'io.github.steverwi:scaffolding:1.0.0'
    }
}
apply plugin: 'io.github.steverwi.scaffold'
```

**A:**
```gradle
plugins {
    id 'io.github.steverwi.scaffold' version '1.0.0'
}
```

**¡Listo para usar ahora mismo!** 🎉