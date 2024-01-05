package xyz.miyayu.attendancereader.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.miyayu.attendancereader.Destinations
import xyz.miyayu.attendancereader.view.route.LoginRoute
import xyz.miyayu.attendancereader.view.route.TopRoute

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.Login.route) {
        composable(Destinations.Login.route) {
            LoginRoute(
                navController = navController
            )
        }
        composable(Destinations.Top.route) {
            TopRoute(
                navController = navController
            )
        }
    }
}