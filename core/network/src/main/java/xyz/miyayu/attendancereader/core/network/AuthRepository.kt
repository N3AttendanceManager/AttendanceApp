package xyz.miyayu.attendancereader.core.network

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInUserInputData

interface AuthRepository {
    suspend fun refreshJwtToken(formData: SignInUserInputData): Result<CredentialData, Throwable>
    suspend fun refreshJwtToken(credentialData: CredentialData): Result<CredentialData, Throwable>
}