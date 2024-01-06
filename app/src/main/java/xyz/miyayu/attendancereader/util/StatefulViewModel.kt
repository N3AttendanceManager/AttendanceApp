package xyz.miyayu.attendancereader.util

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StatefulViewModel<T, H>(
    initialState: H
) : ViewModel() {

    private val _uiState = MutableStateFlow<H>(initialState)
    val uiState = _uiState.asStateFlow()
    
    private val _uiEvents = MutableStateFlow<List<T>>(emptyList())
    val uiEvents = _uiEvents.asStateFlow()

    protected fun setUiState(state: H) {
        Log.d(this::class.simpleName, "setUiState: $state")
        _uiState.value = state
    }

    protected fun addUiEvents(event: T) {
        Log.d(this::class.simpleName, "created UiEvent: $event")
        _uiEvents.value = _uiEvents.value + event
    }

    fun consumeUiEvents(event: T) {
        Log.d(this::class.simpleName, "consumed UiEvent: $event")
        _uiEvents.value = _uiEvents.value.filter { it != event }
    }
}