package xyz.miyayu.attendancereader.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 学科
 */
@Serializable
data class Department(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
