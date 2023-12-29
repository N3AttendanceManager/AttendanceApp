package xyz.miyayu.attendancereader.view.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme


object AttendanceTextFieldDefaults {

    @Composable
    fun colors(
        containerColor: Color = Color(0x24EDFDFF),
        textColor: Color = MaterialTheme.colorScheme.onBackground
    ): TextFieldColors {
        return TextFieldDefaults.colors(
            unfocusedContainerColor = containerColor,
            unfocusedTextColor = textColor,
            focusedContainerColor = containerColor,
            focusedTextColor = textColor,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.primary
            ),

            )
    }
}

@Composable
fun AttendanceTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    borderColor: Color = MaterialTheme.colorScheme.primary,
    colors: TextFieldColors = AttendanceTextFieldDefaults.colors(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = borderColor, shape = RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            colors = colors,
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AttendanceTextFieldPreview() {
    AttendanceReaderTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            AttendanceTextField(
                title = "フォーム / Form",
                value = "入力された値",
                onValueChange = {}
            )

        }

    }
}