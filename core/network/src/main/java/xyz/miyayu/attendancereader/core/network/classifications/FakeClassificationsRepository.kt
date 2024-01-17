package xyz.miyayu.attendancereader.core.network.classifications

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Classifications
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeClassificationsRepository @Inject constructor() : ClassificationsRepository {
    val classifications = listOf(
        Classifications(
            id = 1,
            schoolId = 1,
            name = "出席",
            isDecision = true,
            isClassExclusionDecision = false
        ),
        Classifications(
            id = 2,
            schoolId = 1,
            name = "欠席",
            isDecision = false,
            isClassExclusionDecision = false
        ),
    )

    override suspend fun getClassifications(): Result<List<Classifications>, Throwable> {
        TODO("Not yet implemented")
    }
}