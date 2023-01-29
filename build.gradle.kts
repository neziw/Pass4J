import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Gradle plugins configuration
plugins {
    kotlin("jvm") version "1.7.21"
}

// Project information
group = "xyz.neziw"
version = "1.0"

// Repositories configurations
repositories {
    mavenCentral()
}

// Dependencies configuration
dependencies {
    testImplementation(kotlin("test"))
}

// Tasks
// Test task
tasks.test {
    useJUnitPlatform()
}

// Kotlin Compile
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}