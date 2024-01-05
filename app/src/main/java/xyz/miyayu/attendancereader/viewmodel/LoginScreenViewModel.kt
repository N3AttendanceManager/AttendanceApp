package xyz.miyayu.attendancereader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.michaelbull.result.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.model.credential.SignInFormData
import xyz.miyayu.attendancereader.usecase.auth.SignInWithCacheUseCase
import xyz.miyayu.attendancereader.usecase.auth.SignInWithoutCacheUseCase
import xyz.miyayu.attendancereader.util.UiEventViewModel
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInWithoutCacheUseCase: SignInWithoutCacheUseCase,
    private val signInWithCacheUseCase: SignInWithCacheUseCase
) : UiEventViewModel<LoginScreenViewModel.UiEvent>() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState = _uiState.asStateFlow()

    fun signIn(formData: SignInFormData) {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            signInWithoutCacheUseCase
                .execute(formData = formData)
                .mapBoth(
                    success = {
                        _uiState.value = UiState.Successful
                        addUiEvents(event = UiEvent.Success)
                    },
                    failure = {
                        _uiState.value = UiState.Failed
                        addUiEvents(event = UiEvent.Error)
                    }
                )
        }
    }

    fun loadCredential() {
        _uiState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            signInWithCacheUseCase.execute().mapBoth(
                success = {
                    if (it) {
                        _uiState.value = UiState.Successful
                        addUiEvents(event = UiEvent.Success)
                    } else {
                        _uiState.value = UiState.Failed
                        addUiEvents(event = UiEvent.Error)
                    }
                },
                failure = {
                    _uiState.value = UiState.Failed
                    addUiEvents(event = UiEvent.Error)
                }
            )
        }
    }


    sealed class UiState(
        val isLoginButtonEnabled: Boolean,
        val isShowLoading: Boolean = false
    ) {
        data object Initial : UiState(isLoginButtonEnabled = true)
        data object Loading : UiState(isLoginButtonEnabled = false, isShowLoading = true)
        data object Failed : UiState(isLoginButtonEnabled = true)

        data object Successful : UiState(isLoginButtonEnabled = false)
    }

    sealed class UiEvent() {
        data object Error : UiEvent()
        data object Success : UiEvent()
    }
}