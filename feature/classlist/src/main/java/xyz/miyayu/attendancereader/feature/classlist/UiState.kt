package xyz.miyayu.attendancereader.feature.classlist

sealed interface UiState {
    data object Nothing : UiState
    data object Loading : UiState
}