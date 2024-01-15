package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.designsystem.component.ArAppBar
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.model.Subject

@Composable
internal fun ClassRoute(
    viewModel: ClassViewModel = hiltViewModel(),
    onNewClassClick: (Subject) -> Unit
) {
    val subject by viewModel.department.collectAsState()
    ClassScreen(
        subject = subject,
        onNewClassClick = {
            subject?.let {
                onNewClassClick.invoke(it)
            }
        }
    )
}

@Composable
private fun ClassScreen(
    subject: Subject?,
    onNewClassClick: () -> Unit
) {
    ClassRouteAppBar(
        onNewClassClick = onNewClassClick
    )
}

@Composable
private fun ClassRouteAppBar(
    modifier: Modifier = Modifier,
    onNewClassClick: () -> Unit,
) {
    ArAppBar(
        title = "授業一覧画面",
        actions = {
            IconButton(onClick = onNewClassClick, content = {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            })
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun ClassRouteAppBarPreview() {
    PreviewSurface {
        ClassScreen(subject = Subject(id = 1, name = "英語", departmentId = 1)) {}
    }
}