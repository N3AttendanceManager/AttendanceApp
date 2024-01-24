package xyz.miyayu.attendancereader.core.network

import kotlinx.serialization.json.JsonObject
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
    @GET("student")
    suspend fun getStudents(): Response<JsonObject>
}