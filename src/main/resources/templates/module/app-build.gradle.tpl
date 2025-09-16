plugins {
    id 'org.springframework.boot' version '${SPRING_BOOT_VERSION}'
    id 'io.spring.dependency-management' version '1.1.4'
    id 'java'
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
    // Módulos internos
    implementation project(':shared')
    
    // Spring Boot
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-actuator'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    
    // Spring Modulith
    implementation 'org.springframework.modulith:spring-modulith-starter-core:1.2.3'
    
    // Logging
    implementation 'org.springframework.boot:spring-boot-starter-log4j2'
    
    // Tests
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.modulith:spring-modulith-starter-test:1.2.3'
}

configurations.all {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
}

tasks.named('test') {
    useJUnitPlatform()
}