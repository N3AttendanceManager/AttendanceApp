package xyz.miyayu.attendancereader.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val LOGIN_ROUTE = "login_route"
fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit
) {
    composable(route = LOGIN_ROUTE) {
        LoginRoute(onLoginSuccess = onLoginSuccess)
    }
}

fun NavController.backToLoginScreen() {
    navigate(LOGIN_ROUTE) {
        launchSingleTop = true
    }
}