package xyz.miyayu.attendancereader.datastore

import androidx.datastore.core.DataStore
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import kotlinx.coroutines.flow.Flow
import xyz.miyayu.attendancereader.model.credential.CredentialData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialRepository @Inject constructor(
    private val authDataStore: DataStore<CredentialData>
) {
    fun getCredentialFlow(): Flow<CredentialData> = authDataStore.data
    suspend fun setCredential(credentialData: CredentialData): Result<Unit, Throwable> {
        return runCatching {
            authDataStore.updateData { credentialData }
        }
    }

    suspend fun removeCredential(): Result<Unit, Throwable> {
        return runCatching {
            authDataStore.updateData { CredentialData(jwtToken = null) }
        }
    }
}