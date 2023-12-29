package xyz.miyayu.attendancereader.view.route

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.R
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.common.AttendanceButton
import xyz.miyayu.attendancereader.view.common.AttendanceTextField
import xyz.miyayu.attendancereader.viewmodel.LoginScreenViewModel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {
    LoginScreen(
        modifier = modifier,
        onSignIn = loginScreenViewModel::signIn
    )
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    onSignIn: (String, String) -> Unit
) {
    var id by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        AttendanceTextField(
            title = stringResource(id = R.string.id),
            value = id,
            onValueChange = { id = it },
            modifier = Modifier.padding(top = 16.dp)
        )
        AttendanceTextField(
            title = stringResource(id = R.string.password),
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(top = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        AttendanceButton(
            onClick = { onSignIn.invoke(id, password) },
            text = "ログイン",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    AttendanceReaderTheme {
        Scaffold {
            LoginScreen(modifier = Modifier.padding(it), onSignIn = { _, _ -> })
        }
    }
}