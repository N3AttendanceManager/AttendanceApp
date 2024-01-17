package xyz.miyayu.attendancereader.core.network.attendances

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Attendance
import xyz.miyayu.attendancereader.model.Student

interface AttendanceRepository {
    suspend fun getAttendances(classId: Int): Result<List<Attendance>, Throwable>
    suspend fun registerAttendance(
        idm: String,
        classId: Int,
        classificationId: Int
    ): Result<Student?, Throwable>

    suspend fun registerManualAttendance(
        studentId: Int,
        classId: Int,
        classificationId: Int
    ): Result<Student?, Throwable>
}