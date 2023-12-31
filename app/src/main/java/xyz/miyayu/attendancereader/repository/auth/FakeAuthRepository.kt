package xyz.miyayu.attendancereader.repository.auth

import android.util.Log
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInFormData
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
    override suspend fun fetchNewJwtToken(formData: SignInFormData): Result<CredentialData, Throwable> {
        delay(1000)
        return if (formData.id == "admin" && formData.password == "admin") {
            val algorithm: Algorithm = Algorithm.HMAC256("secret")
            // 1時間後の時間
            val jwtToken = JWT.create()
                .withIssuedAt(Instant.now())
                .withIssuer("Debug Server")
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(algorithm)

            Log.d(this::class.simpleName, "Created JwtToken: $jwtToken")
            return Ok(CredentialData(jwtToken = jwtToken))
        } else
            Err(IllegalStateException("認証に失敗しました"))
    }
}