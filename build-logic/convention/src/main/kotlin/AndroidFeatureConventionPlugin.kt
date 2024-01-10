import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import xyz.miyayu.attendancereader.libs

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("attendancereader.android.library")
                apply("attendancereader.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:designsystem"))
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }
        }
    }

}