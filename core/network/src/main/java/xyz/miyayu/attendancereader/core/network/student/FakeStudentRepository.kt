package xyz.miyayu.attendancereader.core.network.student

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Student
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakeStudentRepository @Inject constructor() : StudentRepository {
    val students: MutableList<Student> = (1..50).map { index ->
        Student(
            id = index,
            studentId = "S$index",
            name = "山田太郎$index",
            departmentId = 1,
            icId = when (index) {
                2 -> "013ac121d5f09e31"
                3 -> "012e5508f8458571"
                else -> if (Random.nextBoolean()) null else "IC$index"
            },
        )
    }.toMutableList()

    override suspend fun getAllStudent(): Result<List<Student>, Throwable> = Ok(students)
    override suspend fun getStudents(departmentId: Int): Result<List<Student>, Throwable> =
        Ok(students.filter { it.departmentId == departmentId })

    override suspend fun updateStudentIc(studentId: Int, icId: String): Result<Unit, Throwable> {
        students.replaceAll { if (it.id == studentId) it.copy(icId = icId) else it }
        return Ok(Unit)
    }

}