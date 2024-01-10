package xyz.miyayu.attendancereader.model

import kotlinx.serialization.Serializable

/**
 * 科目
 */
@Serializable
data class Subject(
    val id: Int,
    val name: String,
    val departmentId: Int
)
