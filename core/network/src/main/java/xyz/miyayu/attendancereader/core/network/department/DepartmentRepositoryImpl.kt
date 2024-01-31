package xyz.miyayu.attendancereader.core.network.department

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import kotlinx.coroutines.async
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.model.Department
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DepartmentRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : DepartmentRepository {
    override suspend fun getAllDepartment(): Result<List<Department>, Throwable> {
        return networkService.getDepartments().toResult()
    }

    override suspend fun getDepartment(departmentId: Int): Result<Department?, Throwable> {
        return binding {
            return@binding async { getAllDepartment().bind<List<Department>>() }.await()
                .firstOrNull<Department> { it.id == departmentId }
        }
    }
}