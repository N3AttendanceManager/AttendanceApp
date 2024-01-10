import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "xyz.miyayu.attendancereader.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradlePlugin)
}
gradlePlugin {
    plugins {
        create("androidApplication") {
            id = "attendancereader.android.application"
            implementationClass =
                "AndroidApplicationConventionPlugin"
        }
        create("androidComposeApplication") {
            id = "attendancereader.android.application.compose"
            implementationClass =
                "AndroidApplicationComposeConventionPlugin"
        }
        create("androidHilt") {
            id = "attendancereader.android.hilt"
            implementationClass =
                "AndroidHiltConventionPlugin"
        }
        create("androidLibraryCompose") {
            id = "attendancereader.android.library.compose"
            implementationClass =
                "AndroidLibraryComposeConventionPlugin"
        }
        create("androidLibrary") {
            id = "attendancereader.android.library"
            implementationClass =
                "AndroidLibraryConventionPlugin"
        }
        create("androidFeature") {
            id = "attendancereader.android.feature"
            implementationClass =
                "AndroidFeatureConventionPlugin"
        }
        create("androidApplicationFlavors") {
            id = "attendancereader.android.application.flavors"
            implementationClass =
                "AndroidApplicationFlavorsConventionPlugin"
        }
        create("jvmLibrary") {
            id = "attendancereader.jvm.library"
            implementationClass =
                "JvmLibraryConventionPlugin"
        }
    }
}