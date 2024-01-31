package xyz.miyayu.attendancereader.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IcCardRegisterBody(
    @SerialName("studentAutoId")
    val studentId: Int,
    @SerialName("icId")
    val icId: String
)
