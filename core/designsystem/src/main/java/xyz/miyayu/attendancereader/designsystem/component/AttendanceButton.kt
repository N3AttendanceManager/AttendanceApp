package xyz.miyayu.attendancereader.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.provider.BooleanPreviewProvider

@Composable
fun AttendanceButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AttendanceButtonPreview(
    @PreviewParameter(BooleanPreviewProvider::class) isEnabled: Boolean
) {
    PreviewSurface {
        AttendanceButton(
            onClick = { },
            text = "ボタン Button",
            enabled = isEnabled
        )
    }
}