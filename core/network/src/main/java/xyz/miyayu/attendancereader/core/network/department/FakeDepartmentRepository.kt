package xyz.miyayu.attendancereader.core.network.department

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import xyz.miyayu.attendancereader.model.Department
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakeDepartmentRepository @Inject constructor(

) : DepartmentRepository {
    private val departments = listOf(
        Department(id = 1, name = "ITスペシャリスト科"),
        Department(id = 2, name = "コンサートイベント科")
    )

    override suspend fun getAllDepartment(): Result<List<Department>, Throwable> {
        delay(Random.nextLong(1000))
        return Ok(departments)
    }

    override suspend fun getDepartment(departmentId: Int): Result<Department?, Throwable> {
        delay(Random.nextLong(100))
        return Ok(departments.find { it.id == departmentId })
    }
}