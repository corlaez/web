import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
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
}

dependencies {
    implementation("com.github.corlaez:web-libk:e8bb85d41a")

    // flexmark
    implementation("com.vladsch.flexmark:flexmark-all:0.64.0")// markdown
    implementation("com.vladsch.flexmark:flexmark-ext-gitlab:0.64.0")// mermaid markdown
    implementation("org.jsoup:jsoup:1.15.3")// transitive vulnerability fix

    // kotlinx-html-jvm
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")// HTML DSL

    // javalin
    implementation("io.javalin:javalin:4.6.7")// HTTP Server
    implementation("org.slf4j:slf4j-simple:2.0.6")// required by javalin
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
