package xyz.miyayu.attendancereader.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import xyz.miyayu.attendancereader.designsystem.component.ArNavigationBar
import xyz.miyayu.attendancereader.designsystem.component.ArNavigationBarItem
import xyz.miyayu.attendancereader.navigation.ArNavHost
import xyz.miyayu.attendancereader.navigation.TopLevelDestination

@Composable
fun ArApp(
    arAppState: ArAppState = rememberArAppState()
) {
    Scaffold(
        bottomBar = {
            ArAppNavigationBar(
                destinations = TopLevelDestination.entries.toTypedArray().toList(),
                currentDestination = arAppState.currentTopLevelDestination,
                onNavigateToDestination = arAppState::navigateToTopLevelDestination
            )
        }
    ) {
        ArNavHost(
            appState = arAppState,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
private fun ArAppNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: TopLevelDestination?,
    modifier: Modifier = Modifier
) {

    ArNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach {
            val selected = currentDestination == it
            ArNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(it) },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Image, contentDescription = stringResource(
                            id = it.titleTextId
                        )
                    )
                },
                label = { Text(text = stringResource(id = it.titleTextId)) }
            )
        }
    }
}