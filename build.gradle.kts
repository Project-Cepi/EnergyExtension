import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    kotlin("plugin.serialization") version "1.4.10"

    // Apply the application plugin to add support for building a jar
    java
}

repositories {
    // Use jcenter for resolving dependencies.
    jcenter()
    mavenCentral()

    // Use mavenCentral
    maven(url = "https://repo1.maven.org/maven2/")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://libraries.minecraft.net")
    maven(url = "https://jitpack.io")
    maven(url = "https://jcenter.bintray.com/")
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    implementation(kotlin("reflect"))

    // Use the JUpiter test library.
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")

    // Compile Minestom into project
    implementation("com.github.Minestom:Minestom:7241dbdcf7")

    // Use the Netty library
    implementation("io.netty:netty-transport-native-epoll:4.1.61.Final")

    // Add OkHTTP3
    implementation("com.squareup.okhttp3", "okhttp", "4.9.0")

    // Use kotlinx serialization
    implementation("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.0.1")

    // Implement Klaxon
    implementation("com.beust:klaxon:5.5")

    // implement KStom
    implementation("com.github.Project-Cepi:KStom:6d054839bf")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> { kotlinOptions.jvmTarget = "11" }
val compileKotlin: KotlinCompile by tasks