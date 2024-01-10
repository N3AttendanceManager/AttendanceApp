package xyz.miyayu.attendancereader

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    contentType
}

// The content for the app can either come from local static data which is useful for demo
// purposes, or from a production backend server which supplies up-to-date, real content.
// These two product flavors reflect this behaviour.
@Suppress("EnumEntryName")
enum class NiaFlavor(
    val dimension: FlavorDimension,
    val applicationIdSuffix: String? = null,
    val isDevServer: Boolean = true
) {
    demo(FlavorDimension.contentType, applicationIdSuffix = ".demo"),
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: NiaFlavor) -> Unit = {}
) {
    commonExtension.apply {
        buildTypes {
            val debug = getByName("debug") {
                buildConfigField("boolean", "IS_DEV_SERVER", "false")
            }
            getByName("release") {
                buildConfigField("boolean", "IS_DEV_SERVER", "false")
            }
            create("debugWithFakeServer") {
                initWith(debug)
                buildConfigField("boolean", "IS_DEV_SERVER", "true")
            }
        }
        buildFeatures {
            buildConfig = true
        }
    }
}