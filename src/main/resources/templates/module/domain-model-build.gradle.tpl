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
    // Shared utilities
    api project(':shared')
    
    // Validation
    api 'jakarta.validation:jakarta.validation-api'
    
    // Tests
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.assertj:assertj-core'
}

tasks.named('test') {
    useJUnitPlatform()
}