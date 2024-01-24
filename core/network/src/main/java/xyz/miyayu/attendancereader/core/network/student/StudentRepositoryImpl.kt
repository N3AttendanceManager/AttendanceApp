package xyz.miyayu.attendancereader.core.network.student

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : StudentRepository {
    override suspend fun getAllStudent(): Result<List<Student>, Throwable> {
        val result = networkService.getStudents()
        if (!result.isSuccessful) {
            return Err(Throwable("失敗!"))
        }

        val jsonObject = result.body()!!["students"]!!
        val decodeResult = json.decodeFromJsonElement<List<Student>>(jsonObject)
        return Ok(decodeResult)
    }

    override suspend fun getStudents(departmentId: Int): Result<List<Student>, Throwable> {
        TODO("Not yet implemented")
    }

    override suspend fun updateStudentIc(studentId: Int, icId: String): Result<Unit, Throwable> {
        TODO("Not yet implemented")
    }
}