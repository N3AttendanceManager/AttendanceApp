package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.model.Student

@Composable
internal fun SettingRoute(
    viewModel: SettingViewModel,
    onStudentSelected: (Student) -> Unit,
) {
    val students by viewModel.students.collectAsState()
    SettingScreen(students = students, onStudentSelected = onStudentSelected)
}

@Composable
private fun SettingScreen(
    students: List<Student>,
    onStudentSelected: (Student) -> Unit
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        items(items = students) { student ->
            StudentCard(
                student = student,
                modifier = Modifier.clickable { onStudentSelected(student) })
        }
    }
}

@Composable
private fun StudentCard(student: Student, modifier: Modifier = Modifier) {
    val icText = student.icId?.let { "IC: 登録済み" } ?: "IC: 未登録"
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Cell(text = student.studentId)
            Cell(text = student.name)
            Cell(text = icText, fontSize = 10.sp)
        }
    }
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
        contentAlignment = Alignment.Center

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


@Preview(widthDp = 360)
@Composable
private fun StudentCardPreview() {
    PreviewSurface {
        StudentCard(
            student = Student(
                id = 1,
                name = "宮崎",
                departmentId = 1,
                studentId = "S00001",
                icId = null
            )
        )
    }
}

@Preview
@Composable
private fun StudentScreenPreview() {
    PreviewSurface {
        SettingScreen(
            students = listOf(
                Student(
                    id = 1,
                    name = "宮崎",
                    departmentId = 1,
                    studentId = "S00001",
                    icId = null
                )
            ),
            onStudentSelected = {}
        )
    }
}