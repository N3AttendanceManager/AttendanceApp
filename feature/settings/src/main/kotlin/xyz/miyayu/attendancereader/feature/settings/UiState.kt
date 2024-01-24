package xyz.miyayu.attendancereader.feature.settings

sealed interface UiState {
    data object Normal : UiState
    data object Loading : UiState
}