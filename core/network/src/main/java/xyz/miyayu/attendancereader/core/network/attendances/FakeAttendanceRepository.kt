package xyz.miyayu.attendancereader.core.network.attendances

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.core.network.student.FakeStudentRepository
import xyz.miyayu.attendancereader.model.Attendance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAttendanceRepository @Inject constructor(
    private val fakeStudentRepository: FakeStudentRepository
) : AttendanceRepository {
    val attendances: MutableList<Attendance> = mutableListOf(
        Attendance(
            studentId = 1,
            classId = 1,
            teacherId = 1,
            classificationId = 1
        )
    )

    override suspend fun getAttendances(classId: Int): Result<List<Attendance>, Throwable> =
        Ok(attendances)
}