package xyz.miyayu.attendancereader.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import xyz.miyayu.attendancereader.feature.clazz.CLASS_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.clazz.navigateToSubjectScreen
import xyz.miyayu.attendancereader.feature.home.HOME_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.home.navigateToHomeScreen
import xyz.miyayu.attendancereader.feature.settings.SETTING_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.settings.navigateToSettingScreen
import xyz.miyayu.attendancereader.navigation.TopLevelDestination


@Composable
fun rememberArAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): ArAppState {
    return remember(navController, coroutineScope) {
        ArAppState(
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

class ArAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_SCREEN_ROUTE -> TopLevelDestination.HOME
            SETTING_SCREEN_ROUTE -> TopLevelDestination.SETTINGS
            CLASS_SCREEN_ROUTE -> TopLevelDestination.CLASS
            else -> null
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHomeScreen(topLevelNavOptions)
            TopLevelDestination.CLASS -> navController.navigateToSubjectScreen(topLevelNavOptions)
            TopLevelDestination.SETTINGS -> navController.navigateToSettingScreen(topLevelNavOptions)
        }
    }
}