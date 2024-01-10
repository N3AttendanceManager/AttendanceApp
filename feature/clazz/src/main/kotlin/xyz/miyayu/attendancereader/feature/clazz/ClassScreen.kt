package xyz.miyayu.attendancereader.feature.clazz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.model.Department
import xyz.miyayu.attendancereader.model.Subject

@Composable
internal fun ClassScreen(
    viewModel: TopViewModel = hiltViewModel(),
    onSubjectSelect: (Subject) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    val subjects by viewModel.subjects.collectAsState()
    val departments by viewModel.departments.collectAsState()

    ClassRoute(
        subjects = subjects,
        departments = departments,
        isSelected = onSubjectSelect
    )
}

@Composable
private fun ClassRoute(
    subjects: List<Subject>,
    departments: List<Department>,
    isSelected: (Subject) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        // 学科毎に授業をグループ化し、学科毎に授業を表示していく。
        subjects.groupBy { subject -> subject.departmentId }
            // 学科番号を学科に変換する
            .map { (departmentId, filteredSubjects) ->
                Pair(getDepartment(departments, departmentId), filteredSubjects)
            }
            // 学科ごとに表示していく
            .forEach { (department, filteredSubjects) ->
                // 学科毎に表示される学科名
                DepartmentTitle(department = department)

                // 学科に属する授業を表示する
                filteredSubjects.forEach { subject ->
                    SubjectTile(
                        subject = subject,
                        isClick = { isSelected.invoke(subject) }
                    )
                }
            }
    }
}

@Composable
private fun DepartmentTitle(department: Department?) {
    val text = department?.name ?: "不明"
    Text(text = text, style = MaterialTheme.typography.titleMedium)
}

@Composable
private fun SubjectTile(
    subject: Subject,
    isClick: () -> Unit
) {
    val text = subject.name
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .clickable(onClick = isClick)
            .padding(vertical = 16.dp, horizontal = 8.dp)
    )
}

/**
 * [Department]のリストから[departmentId]をフィルターして抜き出します
 */
private fun getDepartment(departments: List<Department>, departmentId: Int): Department? {
    return departments.firstOrNull { department -> department.id == departmentId }
}