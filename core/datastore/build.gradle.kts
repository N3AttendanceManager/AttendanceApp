@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
    alias(libs.plugins.attendancereader.android.hilt)
}

android {
    namespace = "xyz.miyayu.attendancereader.datastore"
}

dependencies {
    implementation(libs.tink)
    implementation(libs.androidx.datastore)

    implementation(projects.core.model)
    implementation(libs.kotlin.result)
}