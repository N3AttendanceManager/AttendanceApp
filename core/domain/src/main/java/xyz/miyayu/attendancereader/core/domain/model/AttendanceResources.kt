package xyz.miyayu.attendancereader.core.domain.model

import xyz.miyayu.attendancereader.model.Attendance
import xyz.miyayu.attendancereader.model.Classifications
import xyz.miyayu.attendancereader.model.Student

data class AttendanceResources(
    val attendances: List<Attendance>,
    val students: List<Student>,
    val classifications: List<Classifications>,
)
