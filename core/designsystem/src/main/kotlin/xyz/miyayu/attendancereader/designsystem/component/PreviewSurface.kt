package xyz.miyayu.attendancereader.designsystem.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import xyz.miyayu.attendancereader.designsystem.theme.AttendanceReaderTheme

@Composable
fun PreviewSurface(
    content: @Composable () -> Unit
) {
    AttendanceReaderTheme {
        Surface(color = MaterialTheme.colorScheme.background, content = content)
    }
}