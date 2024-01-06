package xyz.miyayu.attendancereader.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.repository.CredentialRepository
import xyz.miyayu.attendancereader.util.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class TopRouteViewModel @Inject constructor(
    private val credentialRepository: CredentialRepository
) : StatefulViewModel<TopRouteViewModel.UiEvent, Unit>(
    initialState = Unit
) {

    private val _enableSignOut = MutableStateFlow(true)
    val enableSignOut = _enableSignOut.asStateFlow()


    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            _enableSignOut.value = false
            credentialRepository.removeCredential().mapBoth(
                success = {
                    _enableSignOut.value = false
                    addUiEvents(UiEvent.SuccessSignOut)
                    Log.d(this::class.simpleName, "signOut: success")
                },
                failure = {
                    _enableSignOut.value = true
                }
            )
        }
    }

    sealed interface UiEvent {
        data object SuccessSignOut : UiEvent
    }
}