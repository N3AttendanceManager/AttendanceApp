plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.safeargs)
    alias(libs.plugins.daggerHilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "xyz.miyayu.attendancereader"
    compileSdk = 34

    defaultConfig {
        applicationId = "xyz.miyayu.attendancereader"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val isDevServerFieldName = "IS_DEV_SERVER"
        debug {
            buildConfigField("boolean", isDevServerFieldName, "false")
            applicationIdSuffix = ".dev"
        }
        // フェイクサーバーで動作するビルドタイプ
        create("debugWithFakeServer") {
            initWith(buildTypes.getByName("debug"))
            applicationIdSuffix = ".dev.fakeserver"
            buildConfigField("boolean", isDevServerFieldName, "true")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("boolean", isDevServerFieldName, "false")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    buildFeatures {
        dataBinding = true
        compose = true
        buildConfig = true
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

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.compose.uiTooling)

    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.material3)
    implementation(libs.compose.materialIcons)
    implementation(libs.compose.activity)
    implementation(libs.compose.navigation)
    implementation(libs.compose.viewmodel)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.hilt)

    implementation(libs.android.material3)

    implementation(libs.tink)
    implementation(libs.androidx.datastore)

    implementation(libs.daggerHilt.android)
    kapt(libs.daggerHilt.compiler)

    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlin.coroutine.android)

    implementation(libs.kotlin.result)
}