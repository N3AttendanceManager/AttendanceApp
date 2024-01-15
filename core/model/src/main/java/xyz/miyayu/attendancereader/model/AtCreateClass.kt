package xyz.miyayu.attendancereader.model

import java.time.LocalDateTime
import java.time.LocalTime

data class AtCreateClass(
    val id: Int,
    val subjectId: Int,
    val start: LocalTime,
    val end: LocalTime
)
