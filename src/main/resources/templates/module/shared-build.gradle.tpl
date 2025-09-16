plugins {
    id 'java-library'
}

group = '${BASE_PACKAGE}'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_${JAVA_VERSION}
    targetCompatibility = JavaVersion.VERSION_${JAVA_VERSION}
}

repositories {
    mavenCentral()
}

dependencies {
    // Utilidades comunes
    api 'org.apache.commons:commons-lang3:3.14.0'
    api 'com.fasterxml.jackson.core:jackson-annotations'
    
    // Tests
    testImplementation 'org.junit.jupiter:junit-jupiter'
}

tasks.named('test') {
    useJUnitPlatform()
}