import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20-RC"
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
    application
}

group = "com.corlaez"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://www.jitpack.io")
    }
    mavenLocal()
}

dependencies {
//    implementation("com.corlaez:web-libk:1.0-SNAPSHOT")// local
    implementation("com.github.corlaez:web-libk:v0.2.3-alpha")// jitpack
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.languageVersion = "2.0"
    kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
    kotlinOptions.freeCompilerArgs += "-progressive"
    kotlinOptions.freeCompilerArgs += "-version"
    kotlinOptions.freeCompilerArgs += "-no-reflect"
    kotlinOptions.freeCompilerArgs += "-verbose"
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

detekt {
    config = files("config/detekt/detekt-overrides.yml")
    buildUponDefaultConfig = true
}
