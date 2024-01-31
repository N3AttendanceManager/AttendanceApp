package xyz.miyayu.attendancereader.core.network.subject

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.coroutines.binding.binding
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import xyz.miyayu.attendancereader.core.network.NetworkService
import xyz.miyayu.attendancereader.core.network.convert.toResult
import xyz.miyayu.attendancereader.model.Subject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SubjectRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
    private val json: Json
) : SubjectRepository {
    override suspend fun getAllSubject(): Result<List<Subject>, Throwable> {
        return networkService.getSubject().toResult { obj ->
            json.decodeFromJsonElement(obj["subjects"]!!)
        }
    }

    override suspend fun getSubject(subjectId: Int): Result<Subject?, Throwable> {
        return binding {
            return@binding async { getAllSubject().bind() }.await()
                .firstOrNull { it.id == subjectId }
        }
    }
}