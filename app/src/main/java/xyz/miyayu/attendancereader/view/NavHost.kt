package xyz.miyayu.attendancereader.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.miyayu.attendancereader.Destinations
import xyz.miyayu.attendancereader.login.LOGIN_ROUTE
import xyz.miyayu.attendancereader.login.backToLoginScreen
import xyz.miyayu.attendancereader.login.loginScreen
import xyz.miyayu.attendancereader.view.route.TopRoute

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LOGIN_ROUTE) {
        loginScreen(
            onLoginSuccess = { navController.navigate(Destinations.Top.route) }
        )
        composable(Destinations.Top.route) {
            TopRoute(
                onLogout = { navController.backToLoginScreen() }
            )
        }
    }
}