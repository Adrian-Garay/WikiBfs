plugins {
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
    id("application")
    id("com.github.johnrengelman.shadow") version "7.1.2"
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

    //Dependencias de la web y las corutinas
    implementation("org.jsoup:jsoup:1.14.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-io:0.1.16")
}

//mainClass.set("org/example/AppKt")
application {
    mainClass.set("org/wikiracing/AppKt")
}

// JVM Toolchain
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

//"Main-Class" to "org/example/AppKt"
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "org/wikiracing/AppKt"
        )
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

//"Main-Class" to "org/example/AppKt"
tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("")
        manifest {
            attributes(
                "Main-Class" to "org/wikiracing/AppKt"
            )
        }
    }
    build {
        dependsOn(shadowJar)
    }

    distZip {
        dependsOn(shadowJar)
    }

    distTar {
        dependsOn(shadowJar)
    }

    startScripts {
        dependsOn(shadowJar)
    }

    startShadowScripts {
        dependsOn(jar)
    }
}
