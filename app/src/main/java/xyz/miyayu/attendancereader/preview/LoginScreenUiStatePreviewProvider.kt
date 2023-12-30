package xyz.miyayu.attendancereader.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import xyz.miyayu.attendancereader.viewmodel.LoginScreenViewModel

class LoginScreenUiStatePreviewProvider :
    PreviewParameterProvider<LoginScreenViewModel.LoginScreenUiState> {
    override val values: Sequence<LoginScreenViewModel.LoginScreenUiState>
        get() = sequenceOf(
            LoginScreenViewModel.LoginScreenUiState.Initial,
            LoginScreenViewModel.LoginScreenUiState.Loading,
            LoginScreenViewModel.LoginScreenUiState.Failed,
            LoginScreenViewModel.LoginScreenUiState.Successful
        )
}