package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val SETTING_SCREEN_ROUTE = "setting_screen"
private const val TOP_ROUTE = "${SETTING_SCREEN_ROUTE}/top"
private const val SCAN_ROUTE = "${SETTING_SCREEN_ROUTE}/scan"

fun NavController.navigateToSettingScreen(navOptions: NavOptions) =
    navigate(SETTING_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.settingScreen(
    navController: NavController
) {
    navigation(route = SETTING_SCREEN_ROUTE, startDestination = TOP_ROUTE) {
        composable(route = TOP_ROUTE) {
            val viewModel: SettingViewModel = hiltViewModel(
                viewModelStoreOwner = it.rememberParentEntry(navController = navController)
            )
            SettingRoute(
                viewModel = viewModel,
                onStudentSelected = {
                    viewModel.setStudent(it)
                    navController.navigate(SCAN_ROUTE)
                }
            )
        }
        composable(route = SCAN_ROUTE) {
            ScanRoute(
                viewModel = hiltViewModel(
                    viewModelStoreOwner = it.rememberParentEntry(navController = navController)
                ),
                onScanned = {
                    navController.popBackStack()
                }
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