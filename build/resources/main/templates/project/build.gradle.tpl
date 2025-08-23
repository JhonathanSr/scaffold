buildscript {
        repositories {
            mavenLocal()
        }
        dependencies {
            classpath 'com.ajsoftware.scaffold:scaffolding:1.0.0'
        }
    }

plugins {
    id 'org.springframework.boot' version '${SPRING_BOOT_VERSION}'
    id 'io.spring.dependency-management' version '1.1.4'
    id 'java'
}

apply plugin: 'com.ajsoftware.scaffold'

group = '${BASE_PACKAGE}'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_${JAVA_VERSION}
    targetCompatibility = JavaVersion.VERSION_${JAVA_VERSION}
}

def activeProfile = project.hasProperty('profile') ? project.profile : 'dev'

repositories {
    mavenCentral()
    if (activeProfile == 'dev') {
        mavenLocal()
    }
}

configurations.all {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}

dependencies {
    // 🔧 Comunes para todos los perfiles
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-log4j2'
    implementation "org.springframework.modulith:spring-modulith-starter-core:${MODULITH_VERSION}"
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'org.springframework.boot:spring-boot-starter-validation'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'

    // 🧪 Solo en perfil dev
    if (activeProfile == 'dev') {
        implementation 'com.ajsoftware.scaffold:scaffolding:1.0.0' // Plugin interno
        implementation 'org.springframework.boot:spring-boot-devtools' // Hot reload
        implementation 'org.mockito:mockito-core:5.12.0' // Mocks para pruebas locales
        implementation 'org.apache.commons:commons-lang3:3.14.0' // Utilidades para desarrollo
    }

    // 🧪 Solo en perfil test
    if (activeProfile == 'test') {
        testImplementation 'org.junit.jupiter:junit-jupiter-api:5.10.0'
        testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.10.0'
        testImplementation 'org.assertj:assertj-core:3.25.1'
    }

    // @module-dependencies
}

tasks.named('test') {
    useJUnitPlatform()
}