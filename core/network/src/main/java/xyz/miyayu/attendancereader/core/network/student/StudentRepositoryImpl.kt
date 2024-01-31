package xyz.miyayu.attendancereader.core.network.student

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.core.network.model.IcCardRegisterBody
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudentRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : StudentRepository {
    override suspend fun getAllStudent(): Result<List<Student>, Throwable> {
        return networkService.getStudents().toResult {
            json.decodeFromJsonElement<List<Student>>(it["students"]!!)
        }
    }

    override suspend fun getStudents(departmentId: Int): Result<List<Student>, Throwable> {
        return binding {
            val students = async { getAllStudent().bind() }
            return@binding students.await().filter { it.departmentId == departmentId }
        }
    }

    override suspend fun updateStudentIc(studentId: Int, icId: String): Result<Unit, Throwable> {
        val result = networkService.setIcId(
            icCardRegisterBody = IcCardRegisterBody(
                studentId = studentId,
                icId = icId
            )
        )
        return if (result.isSuccessful) Ok(Unit) else Err(Throwable(""))
    }
}