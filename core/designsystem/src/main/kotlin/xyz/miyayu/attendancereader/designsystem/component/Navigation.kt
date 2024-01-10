package xyz.miyayu.attendancereader.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.theme.AttendanceReaderTheme

@Composable
fun ArNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        content = content,
        tonalElevation = 0.dp,
        containerColor = MaterialTheme.colorScheme.primary,
    )
}

@Composable
fun RowScope.ArNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
        )
    )
}

@Preview
@Composable
fun ArNavigationPreview() {
    val items = listOf("Item Hello", "Item Sample", "Item 7")
    AttendanceReaderTheme {
        ArNavigationBar {
            items.forEachIndexed { index, item ->
                ArNavigationBarItem(
                    selected = index == 0,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector = Icons.Filled.AcUnit, contentDescription = null) },
                    label = { Text(text = item) }
                )
            }
        }
    }
}