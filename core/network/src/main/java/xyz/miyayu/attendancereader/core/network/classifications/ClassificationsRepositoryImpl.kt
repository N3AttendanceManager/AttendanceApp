package xyz.miyayu.attendancereader.core.network.classifications

import com.github.michaelbull.result.Result
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.model.Classifications
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClassificationsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : ClassificationsRepository {
    override suspend fun getClassifications(): Result<List<Classifications>, Throwable> {
        return networkService.getAttendanceClasses().toResult {
            json.decodeFromJsonElement(it["classifications"]!!)
        }
    }
}