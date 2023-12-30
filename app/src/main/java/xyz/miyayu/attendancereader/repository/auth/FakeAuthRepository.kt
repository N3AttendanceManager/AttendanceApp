package xyz.miyayu.attendancereader.repository.auth

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.model.credential.SignInFormData
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
        return if (formData.id == "admin" && formData.password == "admin")
            Ok(CredentialData("testJwtToken"))
        else
            Err(IllegalStateException("認証に失敗しました"))
    }
}