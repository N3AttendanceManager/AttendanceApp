package xyz.miyayu.attendancereader.feature.classlist

sealed interface ClassAddUiEvents {
    data object Success : ClassAddUiEvents
    data object Failed : ClassAddUiEvents
}