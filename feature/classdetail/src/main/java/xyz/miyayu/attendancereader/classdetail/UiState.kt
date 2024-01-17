package xyz.miyayu.attendancereader.classdetail

sealed interface UiState {
    data object Normal : UiState
    data object Loading : UiState
}