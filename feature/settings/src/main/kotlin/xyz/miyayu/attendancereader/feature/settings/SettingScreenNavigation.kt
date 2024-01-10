package xyz.miyayu.attendancereader.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SETTING_SCREEN_ROUTE = "setting_screen"

fun NavController.navigateToSettingScreen(navOptions: NavOptions) =
    navigate(SETTING_SCREEN_ROUTE, navOptions)

fun NavGraphBuilder.settingScreen() {
    composable(route = SETTING_SCREEN_ROUTE) {
        SettingScreen()
    }
}