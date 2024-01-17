package xyz.miyayu.attendancereader.classdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

const val CLASS_ID_ROUTE_ARG = "class_id_arg"
const val CLASS_DETAIL_ROUTE = "class_detail_screen/{$CLASS_ID_ROUTE_ARG}"
private const val TOP_ROUTE = "class_detail_screens/top"
private const val SCAN_ROUTE = "class_detail_screens/scan"

fun NavController.navigateToClassDetails(classId: Int) {
    navigate("class_detail_screen/$classId")
}

private fun NavController.navigateToScan() {
    navigate(SCAN_ROUTE)
}

fun NavGraphBuilder.classDetailScreen(
    navController: NavController
) {
    val arguments = listOf(navArgument(CLASS_ID_ROUTE_ARG) { type = NavType.IntType })
    navigation(
        route = CLASS_DETAIL_ROUTE, startDestination = TOP_ROUTE, arguments = arguments
    ) {
        composable(route = TOP_ROUTE) {
            val classDetailViewModel: ClassDetailViewModel = hiltViewModel(
                viewModelStoreOwner = it.rememberParentEntry(navController = navController)
            )
            ClassDetailRoute(viewModel = classDetailViewModel,
                onScanButton = { navController.navigateToScan() })
        }
        composable(route = SCAN_ROUTE) {
            ClassReadRoute(
                viewModel = hiltViewModel(
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