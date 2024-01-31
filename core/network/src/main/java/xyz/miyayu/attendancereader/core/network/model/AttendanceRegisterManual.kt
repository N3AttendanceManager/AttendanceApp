package xyz.miyayu.attendancereader.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceRegisterManual(
    val studentId: Int,
    val classId: Int,
    @SerialName("atClassificationId")
    val classificationId: Int
)
