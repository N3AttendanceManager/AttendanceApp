@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
    alias(libs.plugins.attendancereader.android.hilt)
}

android {
    namespace = "xyz.miyayu.attendancereader.core.domain"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.core.datastore)

    implementation(libs.kotlin.result)

}
