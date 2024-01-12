@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.feature)
    alias(libs.plugins.attendancereader.android.library.compose)
    
    alias(libs.plugins.attendancereader.android.hilt)

}

android {
    namespace = "xyz.miyayu.attendancereader.feature.classlist"
}