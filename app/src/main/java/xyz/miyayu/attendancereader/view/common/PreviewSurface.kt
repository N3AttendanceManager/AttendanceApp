package xyz.miyayu.attendancereader.view.common

import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme

@Composable
fun PreviewSurface(
    content: @Composable () -> Unit
) {
    AttendanceReaderTheme {
        Surface(color = MaterialTheme.colorScheme.background, content = content)
    }
}