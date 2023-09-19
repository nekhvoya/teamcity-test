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
    implementation("io.rest-assured:rest-assured:5.3.2")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.6.0.202305301015-r")
    implementation("commons-io:commons-io:2.13.0")
    implementation("com.github.docker-java:docker-java:3.3.3")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("com.codeborne:selenide-testng:6.17.2")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.7")
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}