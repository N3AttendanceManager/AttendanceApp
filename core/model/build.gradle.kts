plugins {
    alias(libs.plugins.attendancereader.jvm.library)

    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.jwt)

    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.serialization.json)}