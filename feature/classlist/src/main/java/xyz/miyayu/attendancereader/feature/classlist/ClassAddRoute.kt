package xyz.miyayu.attendancereader.feature.classlist

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

internal const val SUBJECT_ADD_ARG = "classId"

internal class ClassAddArgs(val subjectId: Int) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(savedStateHandle.get<Int>(SUBJECT_ADD_ARG)!!)
}

fun NavController.navigateToClassAdd(subjectId: Int) {
    navigate("class_add_route/$subjectId")
}

fun NavGraphBuilder.classAddScreen(
    onClassAddFinished: () -> Unit,
) {
    composable(
        route = "class_add_route/{$SUBJECT_ADD_ARG}",
        arguments = listOf(
            navArgument(SUBJECT_ADD_ARG) { type = NavType.IntType }
        )
    ) {
        ClassAddRoute()
    }
}