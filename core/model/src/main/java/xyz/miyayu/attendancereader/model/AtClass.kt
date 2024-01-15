package xyz.miyayu.attendancereader.model

import java.time.LocalDateTime

data class AtClass(
    val id: Int,
    val subjectId: Int,
    val start: LocalDateTime,
    val end: LocalDateTime
)
