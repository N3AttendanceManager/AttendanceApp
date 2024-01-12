package xyz.miyayu.attendancereader.feature.classlist.state.top

sealed interface SubjectUiState {
    data object Nothing : SubjectUiState
    data object Loading : SubjectUiState
}