package xyz.miyayu.attendancereader.core.network.atclass

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.AtClass
import java.time.LocalDateTime

interface ClassRepository {
    suspend fun getAtClassList(subjectId: Int): Result<List<AtClass>, Throwable>
    suspend fun createAtClass(
        subjectId: Int,
        start: LocalDateTime,
        end: LocalDateTime
    ): Result<Unit, Throwable>
}