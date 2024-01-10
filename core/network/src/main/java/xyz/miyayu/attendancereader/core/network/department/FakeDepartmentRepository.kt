package xyz.miyayu.attendancereader.core.network.department

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Department
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeDepartmentRepository @Inject constructor(

) : DepartmentRepository {
    override suspend fun getAllDepartment(): Result<List<Department>, Throwable> = Ok(
        listOf(
            Department(id = 1, name = "ITスペシャリスト科"),
            Department(id = 2, name = "コンサートイベント科")
        )
    )
}