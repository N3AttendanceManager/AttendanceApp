package xyz.miyayu.attendancereader.core.network.subject

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeSubjectRepository @Inject constructor(

) : SubjectRepository {
    private val subjects = listOf(
        Subject(id = 1, name = "キャリアデザイン1", departmentId = 1),
        Subject(id = 2, name = "キャリアデザイン2", departmentId = 1),
        Subject(id = 3, name = "キャリアデザイン3", departmentId = 1),
        Subject(id = 4, name = "キャリアデザイン4", departmentId = 1),
        Subject(id = 5, name = "キャリアデザイン5", departmentId = 1),
        Subject(id = 6, name = "キャリアデザイン6", departmentId = 1),
        Subject(id = 7, name = "キャリアデザイン7", departmentId = 1),
        Subject(id = 8, name = "キャリアデザイン8", departmentId = 1),
        Subject(id = 9, name = "キャリアデザイン9", departmentId = 1),
        Subject(id = 10, name = "キャリアデザイン10", departmentId = 1),
        Subject(id = 11, name = "キャリアデザイン11", departmentId = 1),
        Subject(id = 12, name = "キャリアデザイン12", departmentId = 1),
        Subject(id = 13, name = "キャリアデザイン13", departmentId = 1),
        Subject(id = 14, name = "キャリアデザイン14", departmentId = 1),
        Subject(id = 15, name = "キャリアデザイン15", departmentId = 1),
        Subject(id = 16, name = "キャリアデザイン16", departmentId = 1),
        Subject(id = 17, name = "キャリアデザイン17", departmentId = 1),
        Subject(id = 18, name = "キャリアデザイン18", departmentId = 1),
        Subject(id = 19, name = "キャリアデザイン19", departmentId = 1),
        Subject(id = 20, name = "キャリアデザイン1", departmentId = 2),
        Subject(id = 21, name = "キャリアデザイン2", departmentId = 2)
    )

    override suspend fun getAllSubject(): Result<List<Subject>, Throwable> = Ok(
        subjects
    )

    override suspend fun getSubject(subjectId: Int): Result<Subject?, Throwable> =
        Ok(subjects.find { it.id == subjectId })
}