package xyz.miyayu.attendancereader.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceRegisterWithIc(
    val icId: String,
    val classId: Int,
    @SerialName("atClassificationId")
    val classificationId: Int
)
