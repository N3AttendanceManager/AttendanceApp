package xyz.miyayu.attendancereader.core.network

import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInUserInputData
import com.github.michaelbull.result.Result
interface AuthRepository {
    suspend fun refreshJwtToken(formData: SignInUserInputData): Result<CredentialData, Throwable>
    suspend fun refreshJwtToken(credentialData: CredentialData): Result<CredentialData, Throwable>
}