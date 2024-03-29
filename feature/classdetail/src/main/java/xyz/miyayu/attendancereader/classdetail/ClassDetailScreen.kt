package xyz.miyayu.attendancereader.classdetail

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.miyayu.attendancereader.designsystem.component.ArAppBar
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.model.Attendance
import xyz.miyayu.attendancereader.model.Classifications
import xyz.miyayu.attendancereader.model.Student

@Composable
internal fun ClassDetailRoute(
    viewModel: ClassDetailViewModel, onScanButton: () -> Unit
) {
    val resources by viewModel.attendanceResources.collectAsState()
    val uiState by viewModel.uiState.collectAsState()


    ClassDetailScreen(
        onScanButtonClick = onScanButton,
        students = resources?.students ?: emptyList(),
        attendances = resources?.attendances ?: emptyList(),
        classifications = resources?.classifications ?: emptyList(),
        onAttendanceManuallySelected = { s, c ->
            viewModel.onAttendanceManualSelected(
                student = s,
                classifications = c
            )
        },
        isLoading = when (uiState) {
            UiState.Loading -> true
            UiState.Normal -> false
        }
    )
}

@Composable
private fun ClassDetailScreen(
    isLoading: Boolean,
    students: List<Student>,
    attendances: List<Attendance>,
    classifications: List<Classifications>,
    onScanButtonClick: () -> Unit,
    onAttendanceManuallySelected: (Student, Classifications) -> Unit,
) {
    var expandedStudent by remember { mutableStateOf<Student?>(null) }


    Column {
        ArAppBar(title = "生徒一覧", actions = {
            IconButton(onClick = onScanButtonClick) {
                Icon(imageVector = Icons.Filled.CreditCard, contentDescription = null)
            }
        })
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(students) { student ->
                val attendance = attendances.firstOrNull { it.studentId == student.id }
                val classification =
                    classifications.firstOrNull { it.id == attendance?.classificationId }
                StudentItem(student = student,
                    classifications = classification,
                    expanded = student == expandedStudent,
                    onOpenRequest = { expandedStudent = null },
                    dropDownItems = {

                        DropDownItems(classifications = classifications,
                            onSelected = {
                                onAttendanceManuallySelected(student, it)
                                expandedStudent = null
                            })
                    },
                    modifier = Modifier.clickable {
                        expandedStudent = student
                    })
            }
        }
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun ColumnScope.DropDownItems(
    classifications: List<Classifications>, onSelected: (Classifications) -> Unit
) {
    classifications.forEach {
        DropdownMenuItem(text = { Text(text = it.name) }, onClick = { onSelected.invoke(it) })
    }
}

@Composable
private fun LazyItemScope.StudentItem(
    student: Student,
    classifications: Classifications?,
    expanded: Boolean,
    dropDownItems: @Composable ColumnScope.() -> Unit,
    onOpenRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
    ) {
        Cell(text = student.studentId)
        Cell(text = student.name)
        Cell(text = classifications?.name ?: "未登録", fontSize = 15.sp)
    }
    DropdownMenu(
        expanded = expanded, onDismissRequest = onOpenRequest, content = dropDownItems
    )
}


@Composable
private fun RowScope.Cell(
    text: String,
    fontSize: TextUnit = 20.sp
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .border(
                width = 0.5.dp,
                color = MaterialTheme.colorScheme.secondary,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            fontSize = fontSize,
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
    }
}


@Preview
@Composable
private fun ClassDetailScreenPreview() {
    PreviewSurface {
        ClassDetailScreen(
            students = listOf(
                Student(
                    id = 1,
                    studentId = "S000A0001",
                    name = "獅子王",
                    departmentId = 1,
                    icId = null

                )
            ),
            attendances = listOf(
                Attendance(
                    studentId = 1,
                    classId = 1,
                    teacherId = 1,
                    classificationId = 1
                )
            ),
            classifications = listOf(
                Classifications(
                    id = 1,
                    schoolId = 1,
                    name = "出席",
                    isDecision = true,
                    isClassExclusionDecision = true
                )
            ),
            onScanButtonClick = { /*TODO*/ },
            onAttendanceManuallySelected = { student: Student, classifications: Classifications -> },
            isLoading = false
        )
    }
}

@Preview
@Composable
private fun StudentItemPreview() {
    PreviewSurface {
        LazyColumn {
            item {
                StudentItem(
                    student = Student(
                        id = 1,
                        studentId = "S000A0001",
                        name = "獅子王",
                        departmentId = 1,
                        icId = null

                    ),
                    classifications = Classifications(
                        id = 1,
                        schoolId = 1,
                        name = "出席",
                        isDecision = true,
                        isClassExclusionDecision = true
                    ),
                    expanded = true,
                    onOpenRequest = { },
                    dropDownItems = { }
                )
                StudentItem(
                    student = Student(
                        id = 1,
                        studentId = "S000A0001",
                        name = "千空",
                        departmentId = 1,
                        icId = null

                    ),
                    classifications = Classifications(
                        id = 1,
                        schoolId = 1,
                        name = "出席",
                        isDecision = true,
                        isClassExclusionDecision = true
                    ),
                    expanded = true,
                    onOpenRequest = { },
                    dropDownItems = { }
                )
            }
        }
    }
}