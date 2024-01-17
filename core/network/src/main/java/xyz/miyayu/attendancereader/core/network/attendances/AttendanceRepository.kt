package xyz.miyayu.attendancereader.core.network.attendances

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Attendance

interface AttendanceRepository {
    suspend fun getAttendances(classId: Int): Result<List<Attendance>, Throwable>
}