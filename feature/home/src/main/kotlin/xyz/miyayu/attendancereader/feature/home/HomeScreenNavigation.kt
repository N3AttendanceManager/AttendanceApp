package xyz.miyayu.attendancereader.feature.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val HOME_SCREEN_ROUTE = "home_screen"

fun NavController.navigateToHomeScreen(navOptions: NavOptions) =
    navigate(HOME_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_SCREEN_ROUTE) {
        HomeScreen()
    }
}