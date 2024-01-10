@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
    alias(libs.plugins.attendancereader.android.library.compose)
}

android {
    namespace = "xyz.miyayu.attendancereader.designsystem"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {

    api(libs.compose.foundation)
    api(libs.compose.runtime)
    api(libs.compose.material3)
    api(libs.compose.materialIcons)
    api(libs.compose.ui)
    api(libs.compose.uiTooling)

    implementation(libs.android.lifecycle.viewmodel)

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}