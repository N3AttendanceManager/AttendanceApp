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
import xyz.miyayu.attendancereader.usecase.auth.SignInWithoutCacheUseCase
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInWithoutCacheUseCase: SignInWithoutCacheUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginScreenUiState>(LoginScreenUiState.Initial)
    val uiState = _uiState.asStateFlow()
    fun signIn(formData: SignInFormData) {
        _uiState.value = LoginScreenUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            signInWithoutCacheUseCase
                .execute(formData = formData)
                .mapBoth(
                    success = { _uiState.value = LoginScreenUiState.Successful },
                    failure = { _uiState.value = LoginScreenUiState.Failed }
                )
        }
    }

    sealed class LoginScreenUiState(
        val isLoginButtonEnabled: Boolean,
        val isShowLoading: Boolean = false
    ) {
        data object Initial : LoginScreenUiState(isLoginButtonEnabled = true)
        data object Loading : LoginScreenUiState(isLoginButtonEnabled = false, isShowLoading = true)
        data object Failed : LoginScreenUiState(isLoginButtonEnabled = true)

        data object Successful : LoginScreenUiState(isLoginButtonEnabled = false)
    }
}