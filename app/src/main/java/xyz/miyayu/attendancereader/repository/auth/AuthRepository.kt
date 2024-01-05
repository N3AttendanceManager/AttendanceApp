package xyz.miyayu.attendancereader.repository.auth

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInFormData

interface AuthRepository {
    suspend fun refreshJwtToken(formData: SignInFormData): Result<CredentialData, Throwable>
    suspend fun refreshJwtToken(credentialData: CredentialData): Result<CredentialData, Throwable>
}