package xyz.miyayu.attendancereader.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import xyz.miyayu.attendancereader.core.network.AuthRepository
import xyz.miyayu.attendancereader.core.network.BuildConfig
import xyz.miyayu.attendancereader.core.network.FakeAuthRepository
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepositoryImpl
import xyz.miyayu.attendancereader.core.network.atclass.FakeClassRepository
import xyz.miyayu.attendancereader.core.network.attendances.AttendanceRepository
import xyz.miyayu.attendancereader.core.network.attendances.FakeAttendanceRepository
import xyz.miyayu.attendancereader.core.network.classifications.ClassificationsRepository
import xyz.miyayu.attendancereader.core.network.classifications.ClassificationsRepositoryImpl
import xyz.miyayu.attendancereader.core.network.classifications.FakeClassificationsRepository
import xyz.miyayu.attendancereader.core.network.department.DepartmentRepository
import xyz.miyayu.attendancereader.core.network.department.DepartmentRepositoryImpl
import xyz.miyayu.attendancereader.core.network.department.FakeDepartmentRepository
import xyz.miyayu.attendancereader.core.network.student.FakeStudentRepository
import xyz.miyayu.attendancereader.core.network.student.StudentRepository
import xyz.miyayu.attendancereader.core.network.student.StudentRepositoryImpl
import xyz.miyayu.attendancereader.core.network.subject.FakeSubjectRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepositoryImpl
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
            fakeAuthRepository
        }
    }

    @Provides
    @Singleton
    fun provideSubjectRepository(
        fakeSubjectRepository: FakeSubjectRepository,
        subjectRepositoryImpl: SubjectRepositoryImpl
    ): SubjectRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeSubjectRepository
        } else {
            subjectRepositoryImpl
        }
    }

    @Provides
    @Singleton
    fun provideDepartmentRepository(
        fakeDepartmentRepository: FakeDepartmentRepository,
        departmentRepositoryImpl: DepartmentRepositoryImpl
    ): DepartmentRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeDepartmentRepository
        } else {
            departmentRepositoryImpl
        }
    }

    @Provides
    @Singleton
    fun provideClassRepository(
        fakeClassRepository: FakeClassRepository,
        classRepositoryImpl: ClassRepositoryImpl
    ): ClassRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeClassRepository
        } else {
            classRepositoryImpl
        }
    }

    @Provides
    @Singleton
    fun provideStudentRepository(
        fakeStudentRepository: FakeStudentRepository,
        studentRepositoryImpl: StudentRepositoryImpl
    ): StudentRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeStudentRepository
        } else {
            studentRepositoryImpl
        }
    }

    @Provides
    @Singleton
    fun provideAttendanceRepository(
        fakeAttendanceRepository: FakeAttendanceRepository
    ): AttendanceRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeAttendanceRepository
        } else {
            fakeAttendanceRepository
        }
    }

    @Provides
    @Singleton
    fun provideClassificationsRepository(
        fakeClassificationsRepository: FakeClassificationsRepository,
        classificationsRepositoryImpl: ClassificationsRepositoryImpl
    ): ClassificationsRepository {
        return if (BuildConfig.IS_DEV_SERVER) {
            fakeClassificationsRepository
        } else {
            classificationsRepositoryImpl
        }
    }
}