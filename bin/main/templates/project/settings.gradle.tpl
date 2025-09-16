pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = '${PROJECT_NAME}'

// 🧠 Perfil activo (por defecto: dev)
def activeProfile = settings.hasProperty('profile') ? settings.profile : 'dev'
def validProfiles = ['dev', 'test', 'prod']

if (!validProfiles.contains(activeProfile)) {
    throw new GradleException("❌ Perfil '${activeProfile}' no reconocido. Usa uno de: ${validProfiles}")
}

// 🎯 Banner visual en consola
println """
───────────────────────────────────────────────
🚀 Proyecto: ${PROJECT_NAME}
🔧 Perfil activo: ${activeProfile}
📦 Módulos incluidos: app, modules, shared
───────────────────────────────────────────────
"""

// 📦 Inclusión de módulos base
include 'app', 'modules', 'shared'

