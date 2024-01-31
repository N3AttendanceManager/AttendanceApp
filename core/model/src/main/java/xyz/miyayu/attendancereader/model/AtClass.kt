package xyz.miyayu.attendancereader.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.miyayu.attendancereader.model.serializer.LocalDateTimeSerializer
import java.time.LocalDateTime

@Serializable
data class AtClass(
    val id: Int,
    val subjectId: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("startOn")
    val start: LocalDateTime,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("endOn")
    val end: LocalDateTime
)
