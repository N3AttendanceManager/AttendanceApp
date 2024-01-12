package xyz.miyayu.attendancereader.feature.classlist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import xyz.miyayu.attendancereader.model.Subject

const val CLASS_SCREEN_ROUTE = "class_screen"

fun NavController.navigateToSubjectScreen(navOptions: NavOptions) =
    navigate(CLASS_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.subjectScreen(
    onSubjectSelect: (Subject) -> Unit
) {
    composable(route = CLASS_SCREEN_ROUTE) {
        SubjectScreen(
            onSubjectSelect = onSubjectSelect
        )
    }
}