@echo off
REM Script de instalación global del AJSoftware Scaffolding Plugin para Windows
echo 🚀 Instalando AJSoftware Scaffolding Plugin...

REM Crear directorio global para el plugin
set PLUGIN_DIR=%USERPROFILE%\.ajsoftware\scaffolding
if not exist "%PLUGIN_DIR%" mkdir "%PLUGIN_DIR%"

REM Descargar o clonar el plugin
if exist "%PLUGIN_DIR%\.git" (
    echo 📦 Actualizando plugin existente...
    cd /d "%PLUGIN_DIR%"
    git pull origin main
) else (
    echo 📦 Descargando plugin...
    git clone https://github.com/tu-usuario/scaffolding.git "%PLUGIN_DIR%"
    cd /d "%PLUGIN_DIR%"
)

REM Compilar y publicar localmente
echo 🔨 Compilando plugin...
gradlew.bat publishToMavenLocal

REM Crear comando global
set GLOBAL_CMD=%USERPROFILE%\bin\ajscaffold.bat
if not exist "%USERPROFILE%\bin" mkdir "%USERPROFILE%\bin"

echo 🔧 Creando comando global en %GLOBAL_CMD%...
(
echo @echo off
echo set PLUGIN_DIR=%USERPROFILE%\.ajsoftware\scaffolding
echo cd /d "%%PLUGIN_DIR%%"
echo gradlew.bat %%*
) > "%GLOBAL_CMD%"

echo ✅ Instalación completada!
echo 💡 Agrega %USERPROFILE%\bin a tu PATH
echo 💡 Uso: ajscaffold createProject --name=miapp --package=com.empresa.miapp
echo 💡 Ayuda: ajscaffold scaffoldHelp
pause