package xyz.miyayu.attendancereader.core.network.classifications

import com.github.michaelbull.result.Result
import xyz.miyayu.attendancereader.model.Classifications

interface ClassificationsRepository {
    suspend fun getClassifications(): Result<List<Classifications>, Throwable>
}