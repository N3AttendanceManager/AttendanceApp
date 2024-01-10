@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
    alias(libs.plugins.attendancereader.android.hilt)
}

android {
    namespace = "xyz.miyayu.attendancereader.core.domain"
}

dependencies {
    api(projects.core.model)

    api(libs.kotlin.result)
    api(libs.kotlin.result.coroutines)

    implementation(projects.core.network)
    implementation(projects.core.datastore)


}
