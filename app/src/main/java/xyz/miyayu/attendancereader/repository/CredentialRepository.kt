package xyz.miyayu.attendancereader.repository

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import xyz.miyayu.attendancereader.model.credential.CredentialData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CredentialRepository @Inject constructor(
    private val authDataStore: DataStore<CredentialData>
) {
    fun getCredentialFlow(): Flow<CredentialData> = authDataStore.data
    suspend fun setCredential(newValue: String){
        authDataStore.updateData { token ->
            token.copy(jwtToken = newValue)
        }
    }
}