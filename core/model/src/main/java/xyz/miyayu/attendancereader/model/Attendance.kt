package xyz.miyayu.attendancereader.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attendance(
    val studentId: Int,
    val classId: Int,
    val teacherId: Int,
    @SerialName("atClassificationId")
    val classificationId: Int
)
