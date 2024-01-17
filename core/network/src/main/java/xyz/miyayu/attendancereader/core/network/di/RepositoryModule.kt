package xyz.miyayu.attendancereader.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.miyayu.attendancereader.core.network.AuthRepository
import xyz.miyayu.attendancereader.core.network.BuildConfig
import xyz.miyayu.attendancereader.core.network.FakeAuthRepository
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.core.network.atclass.FakeClassRepository
import xyz.miyayu.attendancereader.core.network.department.DepartmentRepository
import xyz.miyayu.attendancereader.core.network.department.FakeDepartmentRepository
import xyz.miyayu.attendancereader.core.network.student.FakeStudentRepository
import xyz.miyayu.attendancereader.core.network.student.StudentRepository
import xyz.miyayu.attendancereader.core.network.subject.FakeSubjectRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
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

    @Provides
    @Singleton
    fun provideSubjectRepository(
        fakeSubjectRepository: FakeSubjectRepository,
    ): SubjectRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeSubjectRepository
        } else {
            TODO("本番環境が実装されたらそれを返す")
        }
    }

    @Provides
    @Singleton
    fun provideDepartmentRepository(
        fakeDepartmentRepository: FakeDepartmentRepository,
    ): DepartmentRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeDepartmentRepository
        } else {
            TODO("本番環境が実装されたらそれを返す")
        }
    }

    @Provides
    @Singleton
    fun provideClassRepository(
        fakeClassRepository: FakeClassRepository,
    ): ClassRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeClassRepository
        } else {
            TODO("本番環境が実装されたらそれを返す")
        }
    }

    @Provides
    @Singleton
    fun provideStudentRepository(
        fakeStudentRepository: FakeStudentRepository
    ): StudentRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeStudentRepository
        } else {
            TODO()
        }
    }
}