package xyz.miyayu.attendancereader.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import xyz.miyayu.attendancereader.viewmodel.LoginScreenViewModel

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