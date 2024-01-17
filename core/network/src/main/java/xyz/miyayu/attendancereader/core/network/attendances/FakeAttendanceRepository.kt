package xyz.miyayu.attendancereader.core.network.attendances

import android.util.Log
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.get
import xyz.miyayu.attendancereader.core.network.student.FakeStudentRepository
import xyz.miyayu.attendancereader.core.network.student.StudentRepository
import xyz.miyayu.attendancereader.model.Attendance
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAttendanceRepository @Inject constructor(
    private val fakeStudentRepository: FakeStudentRepository,
    private val studentRepository: StudentRepository,
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

    override suspend fun registerAttendance(
        idm: String,
        classId: Int,
        classificationId: Int
    ): Result<Student?, Throwable> {
        val student =
            studentRepository.getAllStudent().get()!!.firstOrNull { it.icId == idm }
                ?: return Ok(null)

        if (attendances.any { it.studentId == student.id && it.classId == classId }) {
            attendances.replaceAll {
                if (it.studentId == student.id && it.classId == classId) {
                    Log.d("AttendanceRepository", "$it updated.")
                    it.copy(teacherId = 1, classificationId = classificationId)
                } else it
            }
        } else {
            attendances.add(
                Attendance(
                    studentId = student.id,
                    classId = classId,
                    teacherId = 1,
                    classificationId = classificationId
                )
            )
        }
        return Ok(student)
    }
}