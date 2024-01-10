@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.attendancereader.android.library)
}

android {
    namespace = "xyz.miyayu.attendancereader.core.common"
}

dependencies {
}
