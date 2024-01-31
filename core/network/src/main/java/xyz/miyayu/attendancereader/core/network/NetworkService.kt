package xyz.miyayu.attendancereader.core.network

import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import xyz.miyayu.attendancereader.core.network.model.AttendanceRegisterManual
import xyz.miyayu.attendancereader.core.network.model.AttendanceRegisterWithIc
import xyz.miyayu.attendancereader.core.network.model.IcCardRegisterBody
import xyz.miyayu.attendancereader.model.AtCreateClass
import xyz.miyayu.attendancereader.model.Department

interface NetworkService {
    @GET("student")
    suspend fun getStudents(): Response<JsonObject>

    @POST("ic")
    suspend fun setIcId(@Body icCardRegisterBody: IcCardRegisterBody): Response<Unit>

    @GET("department")
    suspend fun getDepartments(): Response<List<Department>>

    @GET("subject")
    suspend fun getSubject(): Response<JsonObject>

    @GET("class")
    suspend fun getClasses(): Response<JsonObject>

    @POST("class")
    suspend fun addClass(@Body atCreateClass: AtCreateClass): Response<Unit>

    @GET("AttendanceClass")
    suspend fun getAttendanceClasses(): Response<JsonObject>

    @POST("attendance/manual")
    suspend fun registerManualAttendance(@Body attendanceRegisterManual: AttendanceRegisterManual): Response<Unit>

    @POST("attendance/ic")
    suspend fun registerIcAttendance(@Body attendanceRegisterWithIc: AttendanceRegisterWithIc): Response<JsonObject>

    @GET("attendance")
    suspend fun getAllAttendances(): Response<JsonObject>
}