package xyz.miyayu.attendancereader.usecase.auth

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.flatMap
import com.github.michaelbull.result.mapBoth
import kotlinx.coroutines.flow.firstOrNull
import xyz.miyayu.attendancereader.model.credential.SignInFormData
import xyz.miyayu.attendancereader.repository.CredentialRepository
import xyz.miyayu.attendancereader.repository.auth.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * ログインフォームデータを用いてサインイン処理を行い、認証データを保存します
 */
@Singleton
class SignInWithCacheUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val credentialRepository: CredentialRepository,
) {
    suspend fun execute(): Result<Boolean, Throwable> {
        val credentialData = credentialRepository.getCredentialFlow().firstOrNull() ?: return Ok(false)
        return authRepository.refreshJwtToken(credentialData)
            .flatMap { credentialRepository.setCredential(it) }
            .mapBoth(
                success = { Ok(true) },
                failure = { Err(it) }
            )
    }
}