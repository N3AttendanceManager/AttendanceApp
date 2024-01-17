package xyz.miyayu.attendancereader.feature.settings

sealed interface UiEvent {
    data object IcScanned: UiEvent
}