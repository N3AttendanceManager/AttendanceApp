package xyz.miyayu.attendancereader.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LoginScreenUiStatePreviewProvider :
    PreviewParameterProvider<LoginScreenViewModel.UiState> {
    override val values: Sequence<LoginScreenViewModel.UiState>
        get() = sequenceOf(
            LoginScreenViewModel.UiState.Initial,
            LoginScreenViewModel.UiState.Loading,
            LoginScreenViewModel.UiState.Failed,
            LoginScreenViewModel.UiState.Successful
        )
}