package xyz.miyayu.attendancereader.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import xyz.miyayu.attendancereader.designsystem.component.ArAppBar
import xyz.miyayu.attendancereader.designsystem.component.ArNavigationBar
import xyz.miyayu.attendancereader.designsystem.component.ArNavigationBarItem
import xyz.miyayu.attendancereader.navigation.ArNavHost
import xyz.miyayu.attendancereader.navigation.TopLevelDestination

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ArApp(
    arAppState: ArAppState = rememberArAppState()
) {
    Scaffold(
        bottomBar = {
            val currentTop = arAppState.currentTopLevelDestination
            ArAppNavigationBar(
                destinations = TopLevelDestination.entries.toTypedArray().toList(),
                currentDestination = arAppState.currentDestination,
                onNavigateToDestination = {
                    arAppState.navigateToTopLevelDestination(
                        it, currentTop
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .consumeWindowInsets(it)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                )
        ) {
            val currentTop = arAppState.currentTopLevelDestination
            if (currentTop != null) {
                ArAppBar(title = stringResource(id = currentTop.titleTextId))
            }
            ArNavHost(
                appState = arAppState,
            )
        }

    }
}

@Composable
private fun ArAppNavigationBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    Log.d("Current", currentDestination?.hierarchy?.toList().toString())
    ArNavigationBar(
        modifier = modifier
    ) {
        destinations.forEach {
            val selected = currentDestination.isTopLevelDestinationInHierarchy(it)
            ArNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination.invoke(it) },
                icon = {
                    Image(
                        painter = painterResource(id = it.drawableResId),
                        contentDescription = stringResource(
                            id = it.titleTextId
                        ),
                        modifier = Modifier.height(18.dp)
                    )
                },
                label = { Text(text = stringResource(id = it.titleTextId)) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false