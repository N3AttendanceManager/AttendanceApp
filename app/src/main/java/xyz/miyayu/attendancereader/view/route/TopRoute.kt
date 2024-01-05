package xyz.miyayu.attendancereader.view.route

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import xyz.miyayu.attendancereader.Destinations
import xyz.miyayu.attendancereader.view.common.AttendanceButton
import xyz.miyayu.attendancereader.viewmodel.TopRouteViewModel

@Composable
fun TopRoute(
    navController: NavController,
    viewModel: TopRouteViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { events ->
            events.forEach { event ->
                when (event) {
                    TopRouteViewModel.UiEvent.SuccessSignOut -> {
                        navController.navigate(Destinations.Login.route) {
                            popUpTo(Destinations.Top.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                }
                viewModel.consumeUiEvents(event = event)
            }

        }
    }

    val isEnable by viewModel.enableSignOut.collectAsState()
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            AttendanceButton(
                onClick = { viewModel.signOut() },
                text = "ログアウト",
                modifier = Modifier.padding(16.dp),
                enabled = isEnable
            )
        }
    }
}