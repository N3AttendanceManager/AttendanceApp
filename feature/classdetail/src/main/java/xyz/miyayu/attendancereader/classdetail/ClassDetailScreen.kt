package xyz.miyayu.attendancereader.classdetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.component.ArAppBar
import xyz.miyayu.attendancereader.model.Classifications
import xyz.miyayu.attendancereader.model.Student

@Composable
internal fun ClassDetailRoute(
    viewModel: ClassDetailViewModel, onScanButton: () -> Unit
) {
    val resources by viewModel.attendanceResources.collectAsState()
    var expandedStudent by remember { mutableStateOf<Student?>(null) }

    Column {
        ArAppBar(title = "生徒一覧", actions = {
            IconButton(onClick = onScanButton) {
                Icon(imageVector = Icons.Filled.CreditCard, contentDescription = null)
            }
        })
        LazyColumn {
            items(resources?.students ?: emptyList()) { student ->
                val attendance = resources?.attendances?.firstOrNull { it.studentId == student.id }
                val classifications =
                    resources?.classifications?.firstOrNull { it.id == attendance?.classificationId }
                StudentItem(student = student,
                    classifications = classifications,
                    expanded = student == expandedStudent,
                    onOpenRequest = { expandedStudent = null },
                    dropDownItems = {
                        DropDownItems(
                            classifications = resources?.classifications ?: emptyList(),
                            onSelected = {
                                viewModel.onAttendanceManualSelected(
                                    student = student, classifications = it
                                )
                                expandedStudent = null
                            })
                    },
                    modifier = Modifier.clickable {
                        expandedStudent = student
                    })
            }
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
        modifier = modifier.padding(16.dp)
    ) {
        Text(text = student.name)
        Text(text = classifications?.name ?: "未登録", modifier = Modifier.width(64.dp))
        DropdownMenu(
            expanded = expanded, onDismissRequest = onOpenRequest, content = dropDownItems
        )
    }

}