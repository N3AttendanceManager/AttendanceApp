package xyz.miyayu.attendancereader.feature.classlist

sealed class UiState(
    val isLoading: Boolean
) {
    data object Nothing : UiState(isLoading = false)
    data object Loading : UiState(isLoading = true)
}