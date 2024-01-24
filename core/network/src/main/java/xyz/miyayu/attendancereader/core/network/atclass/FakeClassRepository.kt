package xyz.miyayu.attendancereader.core.network.atclass

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import xyz.miyayu.attendancereader.model.AtClass
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakeClassRepository @Inject constructor() : ClassRepository {
    private val atClassList = mutableListOf<AtClass>(
        AtClass(
            id = 1,
            subjectId = 1,
            start = LocalDateTime.of(2024, 1, 1, 9, 30),
            end = LocalDateTime.of(2024, 1, 1, 11, 0)
        ),
        AtClass(
            id = 2,
            subjectId = 1,
            start = LocalDateTime.of(2024, 1, 1, 11, 0),
            end = LocalDateTime.of(2024, 1, 1, 12, 40)
        ),
    )
    private var incrementNumber = atClassList.size
    override suspend fun getAtClass(classId: Int): Result<AtClass?, Throwable> {
        delay(Random.nextLong(1000))
        return Ok(atClassList.firstOrNull { atClass -> atClass.id == classId })
    }


    override suspend fun getAtClassList(subjectId: Int): Result<List<AtClass>, Throwable> {
        delay(Random.nextLong(1000))
        return Ok(atClassList.filter { it.subjectId == subjectId })
    }

    override suspend fun createAtClass(
        subjectId: Int,
        start: LocalTime,
        end: LocalTime
    ): Result<Unit, Throwable> {
        delay(Random.nextLong(1000))
        atClassList.add(
            AtClass(
                id = ++incrementNumber,
                subjectId = subjectId,
                start = start.atDate(LocalDate.now()),
                end = end.atDate(LocalDate.now())
            )
        )
        return Ok(Unit)
    }
}