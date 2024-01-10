package xyz.miyayu.attendancereader.feature.clazz.state.top

sealed interface TopUiState {
    data object Nothing : TopUiState
    data object Loading : TopUiState
}