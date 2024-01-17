package xyz.miyayu.attendancereader.core.network.student

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Student

interface StudentRepository {
    suspend fun getAllStudent(): Result<List<Student>, Throwable>
    suspend fun getStudents(departmentId: Int): Result<List<Student>, Throwable>
    suspend fun updateStudentIc(studentId: Int, icId: String): Result<Unit, Throwable>
}