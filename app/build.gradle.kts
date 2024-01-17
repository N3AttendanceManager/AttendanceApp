plugins {
    alias(libs.plugins.attendancereader.android.application)
    alias(libs.plugins.attendancereader.android.application.compose)
    alias(libs.plugins.attendancereader.android.hilt)
    alias(libs.plugins.attendancereader.android.application.flavors)

    id("com.google.dagger.hilt.android")
}

android {
    namespace = "xyz.miyayu.attendancereader"

    defaultConfig {
        applicationId = "xyz.miyayu.attendancereader"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".dev"
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation("androidx.compose.compiler:compiler:1.4.3")// 互換性のあるバージョンに更新


    implementation(libs.android.coreKtx)
    implementation(libs.android.appcompat)

    implementation(libs.android.lifecycle.viewmodel)
    implementation(libs.android.lifecycle.savedstate)
    implementation(libs.android.lifecycle.livedata)


    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)

    implementation(libs.compose.constraintlayout)

    implementation(libs.android.material3)

    implementation(libs.tink)
    implementation(libs.androidx.datastore)

    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.kotlin.coroutine.android)

    implementation(libs.kotlin.result)

    implementation(libs.jwt)

    implementation(projects.core.designsystem)
    implementation(projects.core.model)
    implementation(projects.core.datastore)

    implementation(projects.feature.login)
    implementation(projects.feature.settings)
    implementation(projects.feature.subjects)
    implementation(projects.feature.classlist)
    implementation(projects.feature.classdetail)
}