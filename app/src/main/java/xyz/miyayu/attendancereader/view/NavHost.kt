package xyz.miyayu.attendancereader.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.miyayu.attendancereader.Destinations
import xyz.miyayu.attendancereader.view.route.TopRoute

@Composable
fun NavHost(
    onSignOut: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destinations.Top.route) {
        composable(Destinations.Top.route) {
            TopRoute(
                onLogout = onSignOut
            )
        }
    }
}