plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    //Tests inutiles
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
}

application {
    mainClass.set("org/example/AppKt")
}

// JVM Toolchain
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "org/example/AppKt"
        )
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
