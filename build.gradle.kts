plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.jetbrains.teamcity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.codeborne:selenide:6.17.1")
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("io.rest-assured:rest-assured:5.3.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.awaitility:awaitility:4.2.0")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    testImplementation("org.testng:testng:7.1.0")
    testImplementation("com.codeborne:selenide-testng:6.17.2")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.7")
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}