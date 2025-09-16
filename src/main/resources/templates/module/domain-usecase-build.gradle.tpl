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
    // Domain models
    api project(':modules:${MODULE}:domain:model')
    
    // Shared utilities
    api project(':shared')
    
    // Spring annotations
    api 'org.springframework:spring-context'
    
    // Tests
    testImplementation 'org.junit.jupiter:junit-jupiter'
    testImplementation 'org.mockito:mockito-core'
    testImplementation 'org.assertj:assertj-core'
}

tasks.named('test') {
    useJUnitPlatform()
}