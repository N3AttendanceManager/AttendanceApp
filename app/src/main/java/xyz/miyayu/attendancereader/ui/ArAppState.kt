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
import xyz.miyayu.attendancereader.feature.classlist.CLASS_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.classlist.navigateToSubjectScreen
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
            SETTING_SCREEN_ROUTE -> TopLevelDestination.SETTING
            CLASS_SCREEN_ROUTE -> TopLevelDestination.CLASS
            else -> null
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination,current: TopLevelDestination?) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = topLevelDestination != current
            }

            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.CLASS -> navController.navigateToSubjectScreen(topLevelNavOptions)
            TopLevelDestination.SETTING -> navController.navigateToSettingScreen(topLevelNavOptions)
        }
    }
}