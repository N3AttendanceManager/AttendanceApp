package xyz.miyayu.attendancereader.model

data class Student(
    val id: Int,
    val studentId: String,
    val name: String,
    val departmentId: Int,
    val icId: String?
)
