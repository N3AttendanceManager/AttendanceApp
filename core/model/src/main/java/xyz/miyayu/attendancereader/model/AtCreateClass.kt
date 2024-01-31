package xyz.miyayu.attendancereader.model

import kotlinx.serialization.Serializable
import xyz.miyayu.attendancereader.model.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class AtCreateClass(
    val subjectId: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    val startOn: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    val endOn: LocalDateTime
)
