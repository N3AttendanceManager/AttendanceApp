package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.model.Student

@Composable
internal fun SettingRoute(
    viewModel: SettingViewModel,
    onStudentSelected: (Student) -> Unit,
) {
    val students by viewModel.students.collectAsState()

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
            .padding(8.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = student.studentId)
                Text(text = student.name)
            }
            Text(text = icText)
        }
    }
}

@Composable
private fun SettingScreen() {

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