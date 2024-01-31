@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
    alias(libs.plugins.attendancereader.android.hilt)

    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "xyz.miyayu.attendancereader.core.network"
    defaultConfig {
        buildConfigField("String", "API_SOURCE", "\"${property("apiSource").toString()}\"")
    }
}

dependencies {
    implementation(projects.core.model)

    api(libs.kotlin.result)
    api(libs.kotlin.result.coroutines)

    //モックサーバーで使用する
    implementation(libs.jwt)


    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")

    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.serialization.json)
}