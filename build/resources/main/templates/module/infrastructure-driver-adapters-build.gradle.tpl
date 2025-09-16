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
    // Domain dependencies
    implementation project(':modules:${MODULE}:domain:model')
    implementation project(':modules:${MODULE}:domain:usecase')
    
    // Shared utilities
    implementation project(':shared')
    
    // Spring Web
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // Documentation
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0'
    
    // Tests
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.boot:spring-boot-testcontainers'
}

tasks.named('test') {
    useJUnitPlatform()
}