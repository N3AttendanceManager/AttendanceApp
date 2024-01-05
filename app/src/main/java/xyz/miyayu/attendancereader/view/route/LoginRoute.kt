package xyz.miyayu.attendancereader.view.route

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import xyz.miyayu.attendancereader.Destinations
import xyz.miyayu.attendancereader.R
import xyz.miyayu.attendancereader.model.credential.SignInFormData
import xyz.miyayu.attendancereader.preview.LoginScreenUiStatePreviewProvider
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.common.AttendanceButton
import xyz.miyayu.attendancereader.view.common.AttendanceTextField
import xyz.miyayu.attendancereader.viewmodel.LoginScreenViewModel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        loginScreenViewModel.loadCredential()
        loginScreenViewModel.uiEvents.collect { events ->
            events.forEach { event ->
                when (event) {
                    LoginScreenViewModel.UiEvent.Success -> {
                        loginScreenViewModel.consumeUiEvents(event = event)
                        navController.navigate(Destinations.Top.route){
                            popUpTo(Destinations.Login.route){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }

                    LoginScreenViewModel.UiEvent.Error -> {
                        loginScreenViewModel.consumeUiEvents(event = event)
                        snackBarHostState.showSnackbar(message = "ログインに失敗しました");
                    }
                }
            }
        }
    }

    val uiState by loginScreenViewModel.uiState.collectAsState()
    LoginScreen(
        modifier = modifier,
        onSignIn = loginScreenViewModel::signIn,
        isLoginButtonEnabled = uiState.isLoginButtonEnabled,
        isShowLoading = uiState.isShowLoading,
        snackBarHostState = snackBarHostState
    )
}

@Composable
private fun LoginScreen(
    isLoginButtonEnabled: Boolean,
    isShowLoading: Boolean,
    snackBarHostState: SnackbarHostState,
    onSignIn: (SignInFormData) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = modifier
    ) {
        Box(modifier = Modifier.padding(it)) {
            LoginScreenBody(
                onSignIn = onSignIn,
                isLoginButtonEnabled = isLoginButtonEnabled
            )
            if (isShowLoading)
                LoadingCircle(modifier = modifier.padding(it))
        }
    }
}

@Composable
private fun LoginScreenBody(
    isLoginButtonEnabled: Boolean,
    onSignIn: (SignInFormData) -> Unit,
    modifier: Modifier = Modifier
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
            onClick = {
                val formData = SignInFormData(id = id, password = password)
                onSignIn.invoke(formData)
            },
            text = "ログイン",
            modifier = Modifier.padding(top = 16.dp),
            enabled = isLoginButtonEnabled
        )
    }
}

@Composable
private fun LoadingCircle(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview(
    @PreviewParameter(LoginScreenUiStatePreviewProvider::class) uiState: LoginScreenViewModel.UiState
) {
    AttendanceReaderTheme {
        Scaffold {
            LoginScreen(
                modifier = Modifier.padding(it),
                onSignIn = { _ -> },
                isLoginButtonEnabled = uiState.isLoginButtonEnabled,
                isShowLoading = uiState.isShowLoading,
                snackBarHostState = remember { SnackbarHostState() }
            )
        }
    }
}