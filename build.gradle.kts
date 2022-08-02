import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "com.corlaez"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:markdown-jvm:0.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("io.javalin:javalin:4.6.4")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    kotlinOptions.useK2 = true
    kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
    kotlinOptions.freeCompilerArgs += "-progressive"
    kotlinOptions.freeCompilerArgs += "-version"
    kotlinOptions.freeCompilerArgs += "-Xjdk-release=17"
    kotlinOptions.freeCompilerArgs += "-no-reflect"
    kotlinOptions.freeCompilerArgs += "-verbose"
}

application {
    mainClass.set("MainKt")
}
