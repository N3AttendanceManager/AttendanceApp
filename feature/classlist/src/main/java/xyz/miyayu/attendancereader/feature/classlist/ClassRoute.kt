package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import xyz.miyayu.attendancereader.model.Subject

internal const val SUBJECT_ID_ARG = "subjectId"

internal class ClazzArgs(val subjectId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(savedStateHandle.get<Int>(SUBJECT_ID_ARG)!!)
}

fun NavController.navigateToClasses(subjectId: Int) {
    navigate("classes_route/$subjectId")
}

fun NavGraphBuilder.classesScreen(
    onNewClassClick: (Subject) -> Unit
) {
    composable(
        route = "classes_route/{$SUBJECT_ID_ARG}",
        arguments = listOf(
            navArgument(SUBJECT_ID_ARG) { type = NavType.IntType }
        )
    ) {
        ClassRoute(
            onNewClassClick = onNewClassClick
        )
    }
}