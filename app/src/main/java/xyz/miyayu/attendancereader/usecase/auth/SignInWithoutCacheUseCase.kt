package xyz.miyayu.attendancereader.usecase.auth

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import xyz.miyayu.attendancereader.model.credential.SignInFormData
import xyz.miyayu.attendancereader.repository.CredentialRepository
import xyz.miyayu.attendancereader.repository.auth.AuthRepository
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
    suspend fun execute(formData: SignInFormData): Result<Unit, Throwable> {
        return authRepository.fetchNewJwtToken(formData = formData)
            .flatMap { credentialRepository.setCredential(it) }
    }
}