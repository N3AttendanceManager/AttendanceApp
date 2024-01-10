package xyz.miyayu.attendancereader.core.network

import android.util.Log
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInUserInputData
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton


/**
 * オフラインテスト用のフェイク認証リポジトリ。ID・パスワード共にadmin,adminの場合に認証を成功させる。
 */
@Singleton
class FakeAuthRepository @Inject constructor(
) : AuthRepository {
    override suspend fun refreshJwtToken(formData: SignInUserInputData): Result<CredentialData, Throwable> {
        delay(1000)
        return if (formData.id == "admin" && formData.password == "admin") {
            val jwtToken = createNewJwtToken()
            Log.d(this::class.simpleName, "Created JwtToken: $jwtToken")
            return Ok(CredentialData(jwtToken = jwtToken))
        } else
            Err(IllegalStateException("認証に失敗しました"))
    }

    override suspend fun refreshJwtToken(credentialData: CredentialData): Result<CredentialData, Throwable> {
        delay(1000)
        if (credentialData.jwtToken == null) {
            return Err(IllegalStateException("空のJWTトークンが渡されました"))
        }
        return Ok(CredentialData(jwtToken = createNewJwtToken()))
    }

    private fun createNewJwtToken(): String {
        val algorithm: Algorithm = Algorithm.HMAC256("secret")
        // 1時間後の時間
        return JWT.create()
            .withIssuedAt(Instant.now())
            .withIssuer("Debug Server")
            .withJWTId(UUID.randomUUID().toString())
            .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
            .sign(algorithm)
    }
}