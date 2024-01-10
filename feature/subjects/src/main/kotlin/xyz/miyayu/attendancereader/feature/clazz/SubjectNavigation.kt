package xyz.miyayu.attendancereader.feature.clazz

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val CLASS_SCREEN_ROUTE = "class_screen"

fun NavController.navigateToSubjectScreen(navOptions: NavOptions) =
    navigate(CLASS_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.subjectScreen() {
    composable(route = CLASS_SCREEN_ROUTE) {
        SubjectScreen(
            onSubjectSelect = {}
        )
    }
}