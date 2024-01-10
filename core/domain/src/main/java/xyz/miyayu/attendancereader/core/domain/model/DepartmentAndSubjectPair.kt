package xyz.miyayu.attendancereader.core.domain.model

import xyz.miyayu.attendancereader.model.Department
import xyz.miyayu.attendancereader.model.Subject

data class DepartmentAndSubjectPair(
    val departments: List<Department>,
    val subjects: List<Subject>
)
