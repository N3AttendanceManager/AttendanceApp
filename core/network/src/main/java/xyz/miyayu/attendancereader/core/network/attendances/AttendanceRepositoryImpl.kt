package xyz.miyayu.attendancereader.core.network.attendances

import com.github.michaelbull.result.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.core.network.model.AttendanceRegisterManual
import xyz.miyayu.attendancereader.core.network.model.AttendanceRegisterWithIc
import xyz.miyayu.attendancereader.model.Attendance
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AttendanceRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : AttendanceRepository {
    override suspend fun getAttendances(classId: Int): Result<List<Attendance>, Throwable> {
        return networkService.getAllAttendances().toResult {
            json.decodeFromJsonElement<List<Attendance>>(it["attendances"]!!)
                .filter { attendance -> attendance.classId == classId }
        }
    }

    override suspend fun registerAttendance(
        idm: String,
        classId: Int,
        classificationId: Int
    ): Result<Student?, Throwable> {
        return networkService.registerIcAttendance(
            attendanceRegisterWithIc = AttendanceRegisterWithIc(
                icId = idm,
                classId = classId,
                classificationId = classificationId
            )
        ).toResult {
            val element = it["student"]
            if (element == null) {
                null
            } else {
                json.decodeFromJsonElement(element)
            }
        }
    }

    override suspend fun registerManualAttendance(
        studentId: Int,
        classId: Int,
        classificationId: Int
    ): Result<Unit, Throwable> {
        return networkService.registerManualAttendance(
            attendanceRegisterManual = AttendanceRegisterManual(
                studentId = studentId,
                classId = classId,
                classificationId = classificationId
            )
        ).toResult()
    }
}