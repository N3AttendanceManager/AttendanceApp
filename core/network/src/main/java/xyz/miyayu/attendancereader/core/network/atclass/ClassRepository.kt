package xyz.miyayu.attendancereader.core.network.atclass

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.AtClass
import java.time.LocalDateTime
import java.time.LocalTime

interface ClassRepository {
    suspend fun getAtClassList(subjectId: Int): Result<List<AtClass>, Throwable>
    suspend fun createAtClass(
        subjectId: Int,
        start: LocalTime,
        end: LocalTime
    ): Result<Unit, Throwable>
}