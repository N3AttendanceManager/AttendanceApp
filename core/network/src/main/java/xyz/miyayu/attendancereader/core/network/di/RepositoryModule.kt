package xyz.miyayu.attendancereader.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.miyayu.attendancereader.core.network.AuthRepository
import xyz.miyayu.attendancereader.core.network.BuildConfig
import xyz.miyayu.attendancereader.core.network.FakeAuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        fakeAuthRepository: FakeAuthRepository,
    ): AuthRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeAuthRepository
        } else {
            TODO("本番環境が実装されたらそれを返す")
        }
    }
}