package xyz.miyayu.attendancereader.model

data class Attendance(
    val studentId: Int,
    val classId: Int,
    val teacherId: Int,
    val classificationId: Int
)
