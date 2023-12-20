package xyz.miyayu.attendancereader.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.util.TokenSerializer
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun authDataStore(
        @ApplicationContext context: Context,
        tokenSerializer: TokenSerializer
    ): DataStore<CredentialData> {
        return DataStoreFactory.create(
            serializer = tokenSerializer,
            produceFile = { context.dataStoreFile("credential.pb") }
        )
    }
}
