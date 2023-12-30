package xyz.miyayu.attendancereader.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.repository.CredentialRepository
import javax.inject.Inject

@HiltViewModel
class SettingTestViewModel @Inject constructor(
    private val credentialRepository: CredentialRepository
) : ViewModel() {
    val dataFlow = credentialRepository.getCredentialFlow()
    fun save(newValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            credentialRepository.setCredential(CredentialData(jwtToken = newValue))
        }
    }
}