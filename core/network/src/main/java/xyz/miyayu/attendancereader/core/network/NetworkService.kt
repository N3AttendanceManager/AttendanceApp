package xyz.miyayu.attendancereader.core.network

import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import xyz.miyayu.attendancereader.core.network.model.IcCardRegisterBody
import xyz.miyayu.attendancereader.model.Department

interface NetworkService {
    @GET("student")
    suspend fun getStudents(): Response<JsonObject>

    @POST("ic")
    suspend fun setIcId(@Body icCardRegisterBody: IcCardRegisterBody): Response<Unit>

    @GET("department")
    suspend fun getDepartments(): Response<List<Department>>
}