plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
}

repositories {
    mavenCentral()
}

object Versions {
    const val kostest = "5.0.3"
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${Versions.kostest}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${Versions.kostest}")
}