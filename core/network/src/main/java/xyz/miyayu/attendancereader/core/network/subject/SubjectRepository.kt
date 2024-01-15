package xyz.miyayu.attendancereader.core.network.subject

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Subject

interface SubjectRepository {
    suspend fun getAllSubject(): Result<List<Subject>, Throwable>
    suspend fun getSubject(subjectId: Int): Result<Subject?, Throwable>
}