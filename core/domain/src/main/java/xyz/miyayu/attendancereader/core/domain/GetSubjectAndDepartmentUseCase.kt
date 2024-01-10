package xyz.miyayu.attendancereader.core.domain

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import kotlinx.coroutines.async
import xyz.miyayu.attendancereader.core.domain.model.DepartmentAndSubjectPair
import xyz.miyayu.attendancereader.core.network.department.DepartmentRepository
import xyz.miyayu.attendancereader.core.network.subject.SubjectRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSubjectAndDepartmentUseCase @Inject constructor(
    private val departmentRepository: DepartmentRepository,
    private val subjectRepository: SubjectRepository,
) {
    suspend fun execute(): Result<DepartmentAndSubjectPair, Throwable> {
        return binding {
            val departments = async { departmentRepository.getAllDepartment().bind() }
            val subjects = async { subjectRepository.getAllSubject().bind() }

            return@binding DepartmentAndSubjectPair(departments.await(), subjects.await())
        }
    }
}