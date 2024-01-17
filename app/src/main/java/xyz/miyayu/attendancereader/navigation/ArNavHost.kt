package xyz.miyayu.attendancereader.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import xyz.miyayu.attendancereader.classdetail.classDetailScreen
import xyz.miyayu.attendancereader.classdetail.navigateToClassDetails
import xyz.miyayu.attendancereader.feature.classlist.CLASS_SCREEN_ROUTE
import xyz.miyayu.attendancereader.feature.classlist.classesNavigation
import xyz.miyayu.attendancereader.feature.classlist.navigateToClasses
import xyz.miyayu.attendancereader.feature.classlist.subjectScreen
import xyz.miyayu.attendancereader.feature.settings.settingScreen
import xyz.miyayu.attendancereader.ui.ArAppState

@Composable
fun ArNavHost(
    appState: ArAppState,
    modifier: Modifier = Modifier
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = CLASS_SCREEN_ROUTE,
        modifier = modifier
    ) {
        settingScreen(
            navController = navController
        )
        classesNavigation(
            navController = navController,
            onClassSelected = { atClass ->
                navController.navigateToClassDetails(classId = atClass.id)
            }
        )
        subjectScreen(
            onSubjectSelect = { subject ->
                navController.navigateToClasses(subjectId = subject.id)
            }
        )
        classDetailScreen(
            navController = navController
        )
    }
}