import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import xyz.miyayu.attendancereader.configureFlavors
import xyz.miyayu.attendancereader.configureKotlinAndroid

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureFlavors(this)
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                //resourcePrefix =
                //    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                //        .lowercase() + "_"
            }
        }
    }
}