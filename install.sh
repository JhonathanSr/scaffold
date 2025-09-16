#!/bin/bash

# Script de instalación global del AJSoftware Scaffolding Plugin
echo "🚀 Instalando AJSoftware Scaffolding Plugin..."

# Crear directorio global para el plugin
PLUGIN_DIR="$HOME/.ajsoftware/scaffolding"
mkdir -p "$PLUGIN_DIR"

# Descargar o clonar el plugin
if [ -d "$PLUGIN_DIR/.git" ]; then
    echo "📦 Actualizando plugin existente..."
    cd "$PLUGIN_DIR"
    git pull origin main
else
    echo "📦 Descargando plugin..."
    git clone https://github.com/tu-usuario/scaffolding.git "$PLUGIN_DIR"
    cd "$PLUGIN_DIR"
fi

# Compilar y publicar localmente
echo "🔨 Compilando plugin..."
./gradlew publishToMavenLocal

# Crear comando global
GLOBAL_CMD="/usr/local/bin/ajscaffold"
echo "🔧 Creando comando global en $GLOBAL_CMD..."

sudo tee "$GLOBAL_CMD" > /dev/null << 'EOF'
#!/bin/bash
PLUGIN_DIR="$HOME/.ajsoftware/scaffolding"
cd "$PLUGIN_DIR"
./gradlew "$@"
EOF

sudo chmod +x "$GLOBAL_CMD"

echo "✅ Instalación completada!"
echo "💡 Uso: ajscaffold createProject --name=miapp --package=com.empresa.miapp"
echo "💡 Ayuda: ajscaffold scaffoldHelp"