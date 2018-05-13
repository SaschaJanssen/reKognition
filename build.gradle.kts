plugins {
    base
    kotlin("jvm") version "1.2.41"
    id("org.jetbrains.kotlin.plugin.spring") version "1.2.41"

    id("org.springframework.boot") version "2.0.2.RELEASE"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

group = "com.rdk.rekognition"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.spring.io/milestone")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compile(kotlin("stdlib"))
    compile("org.springframework.boot:spring-boot-starter-web")
    testCompile("org.springframework.boot:spring-boot-starter-test")
}