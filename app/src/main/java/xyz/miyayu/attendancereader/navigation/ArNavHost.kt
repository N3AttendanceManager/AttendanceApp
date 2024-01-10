package xyz.miyayu.attendancereader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import xyz.miyayu.attendancereader.feature.clazz.subjectScreen
import xyz.miyayu.attendancereader.feature.home.HOME_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.home.homeScreen
import xyz.miyayu.attendancereader.feature.settings.settingScreen
import xyz.miyayu.attendancereader.ui.ArAppState

@Composable
fun ArNavHost(
    appState: ArAppState,
    modifier: Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
        modifier = modifier
    ) {
        homeScreen()
        settingScreen()
        subjectScreen()
    }
}