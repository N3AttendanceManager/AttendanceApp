package xyz.miyayu.attendancereader.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.miyayu.attendancereader.BuildConfig
import xyz.miyayu.attendancereader.repository.auth.AuthRepository
import xyz.miyayu.attendancereader.repository.auth.FakeAuthRepository
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