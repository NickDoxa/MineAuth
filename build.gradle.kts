plugins {
    java
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "net.oasisgames"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.processResources {
    dependsOn("copyFrontendToBuild")
}

tasks.bootJar {
    archiveFileName = "app.jar"
}

task("copyFrontendToBuild", type = Copy::class) {
    dependsOn("npmBuild")
    from("$projectDir/mineauth-frontend/dist/")
    into("$buildDir/resources/main/static")
}

task("npmBuild", type = Exec::class) {
    workingDir("mineauth-frontend/")
    commandLine("npm.cmd", "run", "build")
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mapstruct:mapstruct:1.6.0")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
