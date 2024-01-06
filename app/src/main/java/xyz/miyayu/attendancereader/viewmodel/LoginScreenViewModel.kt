package xyz.miyayu.attendancereader.viewmodel

import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.model.credential.SignInFormData
import xyz.miyayu.attendancereader.usecase.auth.SignInWithCacheUseCase
import xyz.miyayu.attendancereader.usecase.auth.SignInWithoutCacheUseCase
import xyz.miyayu.attendancereader.util.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInWithoutCacheUseCase: SignInWithoutCacheUseCase,
    private val signInWithCacheUseCase: SignInWithCacheUseCase
) : StatefulViewModel<LoginScreenViewModel.UiEvent, LoginScreenViewModel.UiState>(
    initialState = UiState.Initial
) {

    fun signIn(formData: SignInFormData) {
        setUiState(UiState.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            signInWithoutCacheUseCase.execute(formData = formData).mapBoth(success = {
                setUiState(UiState.Successful)
                addUiEvents(event = UiEvent.Success)
            }, failure = {
                setUiState(UiState.Failed)
                addUiEvents(event = UiEvent.Error)
            })
        }
    }

    fun loadCredential() {
        setUiState(UiState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            signInWithCacheUseCase.execute().mapBoth(success = {
                if (it) {
                    setUiState(UiState.Successful)
                    addUiEvents(event = UiEvent.Success)
                } else {
                    setUiState(UiState.Failed)
                    addUiEvents(event = UiEvent.Error)
                }
            }, failure = {
                setUiState(UiState.Failed)
                addUiEvents(event = UiEvent.Error)
            })
        }
    }


    sealed class UiState(
        val isLoginButtonEnabled: Boolean, val isShowLoading: Boolean = false
    ) {
        data object Initial : UiState(isLoginButtonEnabled = true)
        data object Loading : UiState(isLoginButtonEnabled = false, isShowLoading = true)
        data object Failed : UiState(isLoginButtonEnabled = true)

        data object Successful : UiState(isLoginButtonEnabled = false)
    }

    sealed class UiEvent {
        data object Error : UiEvent()
        data object Success : UiEvent()
    }
}