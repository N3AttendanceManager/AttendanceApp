package xyz.miyayu.attendancereader.feature.clazz.state.top

sealed interface SubjectUiState {
    data object Nothing : SubjectUiState
    data object Loading : SubjectUiState
}