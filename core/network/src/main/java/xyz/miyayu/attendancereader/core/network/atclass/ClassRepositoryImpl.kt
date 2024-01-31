package xyz.miyayu.attendancereader.core.network.atclass

import com.github.michaelbull.result.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.model.AtClass
import xyz.miyayu.attendancereader.model.AtCreateClass
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : ClassRepository {
    override suspend fun getAtClass(classId: Int): Result<AtClass?, Throwable> {
        return networkService.getClasses().toResult { jsonObject ->
            json
                .decodeFromJsonElement<List<AtClass>>(jsonObject["classes"]!!)
                .firstOrNull { it.id == classId }
        }
    }

    override suspend fun getAtClassList(subjectId: Int): Result<List<AtClass>, Throwable> {
        return networkService.getClasses().toResult { jsonObject ->
            json
                .decodeFromJsonElement<List<AtClass>>(jsonObject["classes"]!!)
                .filter { it.subjectId == subjectId }
        }
    }

    override suspend fun createAtClass(
        subjectId: Int,
        start: LocalTime,
        end: LocalTime
    ): Result<Unit, Throwable> {
        val date = LocalDate.now()
        val startOn = LocalDateTime.of(date, start)
        val endOn = LocalDateTime.of(date, end)

        return networkService.addClass(
            atCreateClass = AtCreateClass(
                subjectId = subjectId,
                startOn = startOn,
                endOn = endOn
            )
        ).toResult()
    }
}