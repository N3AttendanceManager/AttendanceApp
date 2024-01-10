package xyz.miyayu.attendancereader.core.domain

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import xyz.miyayu.attendancereader.core.network.AuthRepository
import xyz.miyayu.attendancereader.model.credential.SignInUserInputData
import xyz.miyayu.attendancereader.datastore.CredentialRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ログインフォームデータを用いてサインイン処理を行い、認証データを保存します
 */
@Singleton
class SignInWithoutCacheUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val credentialRepository: CredentialRepository,
) {
    suspend fun execute(formData: SignInUserInputData): Result<Unit, Throwable> {
        return authRepository.refreshJwtToken(formData = formData)
            .flatMap { credentialRepository.setCredential(it) }
    }
}