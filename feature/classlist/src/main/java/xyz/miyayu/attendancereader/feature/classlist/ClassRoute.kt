package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

internal const val SUBJECT_ID_ARG = "subjectId"

internal class ClazzArgs(val subjectId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(savedStateHandle.get<Int>(SUBJECT_ID_ARG)!!)
}

fun NavController.navigateToClasses(subjectId: Int) {
    navigate("classes_route/$subjectId")
}

private fun NavController.navigateToNewClass() {
    navigate("classes/new")
}

fun NavGraphBuilder.classesNavigation(
    navController: NavController
) {
    navigation(startDestination = "classes/top",
        route = "classes_route/{$SUBJECT_ID_ARG}",
        arguments = listOf(navArgument(SUBJECT_ID_ARG) { type = NavType.IntType })) {
        composable(
            route = "classes/top",
        ) {
            ClassRoute(
                onNewClassClick = {
                    navController.navigateToNewClass()
                }, viewModel = hiltViewModel(
                    viewModelStoreOwner = it.rememberParentEntry(navController = navController)
                )
            )
        }
        composable(
            route = "classes/new",
        ) {
            ClassAddRoute(
                onCreated = {
                    navController.popBackStack()
                }, viewModel = hiltViewModel(
                    viewModelStoreOwner = it.rememberParentEntry(navController = navController)
                )
            )
        }
    }
}

@Composable
private fun NavBackStackEntry.rememberParentEntry(navController: NavController): NavBackStackEntry {
    // First, get the parent of the current destination
    // This always exists since every destination in your graph has a parent
    val parentId = this.destination.parent!!.id

    // Now get the NavBackStackEntry associated with the parent
    // making sure to remember it
    return remember(this) {
        navController.getBackStackEntry(parentId)
    }
}