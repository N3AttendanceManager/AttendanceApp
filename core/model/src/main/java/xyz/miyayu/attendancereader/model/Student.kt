package xyz.miyayu.attendancereader.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val studentId: String,
    val name: String,
    val departmentId: Int,
    val icId: String?
)
