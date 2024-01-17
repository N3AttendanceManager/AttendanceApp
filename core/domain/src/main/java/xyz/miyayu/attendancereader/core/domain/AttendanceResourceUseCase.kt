package xyz.miyayu.attendancereader.core.domain

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import kotlinx.coroutines.async
import xyz.miyayu.attendancereader.core.domain.model.AttendanceResources
import xyz.miyayu.attendancereader.core.network.atclass.ClassRepository
import xyz.miyayu.attendancereader.core.network.attendances.AttendanceRepository
import xyz.miyayu.attendancereader.core.network.classifications.ClassificationsRepository
import xyz.miyayu.attendancereader.core.network.student.StudentRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import javax.inject.Inject

class AttendanceResourceUseCase @Inject constructor(
    private val classRepository: ClassRepository,
    private val subjectRepository: SubjectRepository,
    private val attendanceRepository: AttendanceRepository,
    private val studentRepository: StudentRepository,
    private val classificationsRepository: ClassificationsRepository,
) {
    suspend fun execute(classId: Int): Result<AttendanceResources, Throwable> {
        return binding {

            val atClass =
                async { classRepository.getAtClass(classId = classId).bind() }.await()
                    ?: throw IllegalStateException("無効な授業が渡されました")

            val subject =
                async { subjectRepository.getSubject(subjectId = atClass.subjectId).bind() }.await()
                    ?: throw IllegalStateException("授業から科目を特定することができませんでした。")

            val attendances =
                async { attendanceRepository.getAttendances(classId = classId).bind() }

            val students =
                async { studentRepository.getStudents(departmentId = subject.departmentId).bind() }

            val classifications =
                async { classificationsRepository.getClassifications().bind() }

            return@binding AttendanceResources(
                attendances = attendances.await(),
                students = students.await(),
                classifications = classifications.await(),
            )
        }
    }
}