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
    
    // Spring Data JPA
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    
    // Database
    runtimeOnly 'com.h2database:h2'
    runtimeOnly 'org.postgresql:postgresql'
    
    // Tests
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.testcontainers:postgresql'
}

tasks.named('test') {
    useJUnitPlatform()
}