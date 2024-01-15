package xyz.miyayu.attendancereader.core.network.department

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Department

interface DepartmentRepository {
    suspend fun getAllDepartment(): Result<List<Department>, Throwable>
    suspend fun getDepartment(departmentId: Int): Result<Department?, Throwable>
}