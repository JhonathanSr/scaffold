# 📖 Manual de Publicación - Gradle Plugin Portal

## 🎯 Objetivo
Publicar el plugin AJSoftware Scaffolding en Gradle Plugin Portal para uso público.

---

## 📋 Prerrequisitos
- ✅ Cuenta de GitHub
- ✅ Plugin compilado y funcionando localmente
- ✅ Conexión a internet

---

## 🚀 Paso a Paso

### 1. Crear cuenta en Gradle Plugin Portal

1. **Ir al sitio web**
   - Abrir navegador en: https://plugins.gradle.org

2. **Registrarse**
   - Click en "Log in" (esquina superior derecha)
   - Click en "Sign up with GitHub"
   - Autorizar la aplicación con tu cuenta GitHub
   - Completar el perfil si es necesario

### 2. Obtener credenciales API

1. **Acceder al perfil**
   - Una vez logueado, click en tu avatar (esquina superior derecha)
   - Seleccionar "API Keys"

2. **Generar keys**
   - Click en "Generate new API key"
   - **IMPORTANTE**: Copiar y guardar inmediatamente:
     - `Key`: (ejemplo: abcd1234...)
     - `Secret`: (ejemplo: xyz9876...)
   - ⚠️ **No podrás ver el secret nuevamente**

### 3. Configurar credenciales localmente

1. **Crear directorio Gradle** (si no existe)
   ```cmd
   mkdir %USERPROFILE%\.gradle
   ```

2. **Crear archivo de configuración**
   - Crear archivo: `%USERPROFILE%\.gradle\gradle.properties`
   - Agregar contenido:
   ```properties
   gradle.publish.key=TU_API_KEY_AQUI
   gradle.publish.secret=TU_SECRET_AQUI
   ```
   - **Reemplazar** `TU_API_KEY_AQUI` y `TU_SECRET_AQUI` con tus valores reales

### 4. Validar configuración del plugin

1. **Abrir terminal** en el directorio del plugin:
   ```cmd
   cd e:\Repositorios\AdvanceInventory\scaffolding
   ```

2. **Validar plugin**:
   ```cmd
   call gradlew.bat validatePlugins
   ```
   - ✅ Debe mostrar "BUILD SUCCESSFUL"

### 5. Publicar el plugin

1. **Ejecutar comando de publicación**:
   ```cmd
   call gradlew.bat publishPlugins
   ```

2. **Esperar confirmación**:
   - ✅ "BUILD SUCCESSFUL" = Publicación exitosa
   - ❌ Si hay errores, revisar credenciales y configuración

### 6. Verificar publicación

1. **Buscar en Plugin Portal**
   - Ir a: https://plugins.gradle.org
   - Buscar: "com.ajsoftware.scaffold"
   - Debe aparecer tu plugin

2. **Tiempo de disponibilidad**
   - Puede tomar 10-30 minutos en estar disponible públicamente

---

## 🎯 Uso del plugin publicado

Una vez publicado, cualquier equipo puede usar:

### Configuración en proyecto
```gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

### Comandos disponibles
```bash
# Crear proyecto Spring Modulith
./gradlew createProject -Pname=miapp -Ppackage=com.empresa.miapp

# Crear módulo con arquitectura hexagonal
./gradlew createModule -Pmodule=products

# Agregar componentes
./gradlew addComponent -Pmodule=products -Ptype=usecase -Pname=CreateProduct
./gradlew addComponent -Pmodule=products -Ptype=controller -Pname=Product
./gradlew addComponent -Pmodule=products -Ptype=repository -Pname=Product
./gradlew addComponent -Pmodule=products -Ptype=model -Pname=Product
```

---

## 🔧 Solución de problemas

### Error: "Invalid credentials"
- ✅ Verificar que las credenciales en `gradle.properties` sean correctas
- ✅ Regenerar API keys si es necesario

### Error: "Plugin validation failed"
- ✅ Ejecutar `gradlew validatePlugins` para ver detalles
- ✅ Verificar que `build.gradle` tenga la configuración correcta

### Error: "Plugin ID already exists"
- ✅ Cambiar el ID del plugin en `build.gradle`
- ✅ Usar un namespace único (ej: `com.tuempresa.scaffold`)

---

## 📞 Contacto y soporte

- **Autor**: Jhonathan - AJSoftware
- **Repositorio**: https://github.com/SteverWiw/scaffolding
- **Issues**: Crear issue en GitHub para reportar problemas

---

## 🎉 ¡Listo!

Tu plugin estará disponible públicamente para que cualquier desarrollador pueda usarlo con:

```gradle
plugins {
    id 'com.ajsoftware.scaffold' version '1.0.0'
}
```

**¡Felicidades por contribuir a la comunidad de desarrolladores!** 🚀