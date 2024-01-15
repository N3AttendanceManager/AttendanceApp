package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.designsystem.component.ArAppBar
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.model.AtClass
import xyz.miyayu.attendancereader.model.Subject
import java.time.LocalDateTime

@Composable
internal fun ClassRoute(
    viewModel: ClassViewModel = hiltViewModel(),
    onNewClassClick: (Subject) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setSubjectId(1)
        viewModel.fetchClassList()
        viewModel.fetchSubject()
    }
    val subject by viewModel.department.collectAsState()
    val classList by viewModel.atClassList.collectAsState()
    ClassScreen(
        classList = classList,
        onNewClassClick = {
            subject?.let {
                onNewClassClick.invoke(it)
            }
        }
    )
}

@Composable
private fun ClassScreen(
    classList: List<AtClass>,
    onNewClassClick: () -> Unit
) {
    Column {
        ClassRouteAppBar(
            onNewClassClick = onNewClassClick
        )
        classList.forEach { atClass ->
            //TODO タイル状にする
            Text(text = atClass.start.toString())
        }

    }
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
        ClassScreen(
            classList = listOf(
                AtClass(
                    id = 1,
                    subjectId = 1,
                    start = LocalDateTime.of(2024, 1, 1, 9, 30),
                    end = LocalDateTime.of(2024, 1, 1, 11, 0)
                ),
                AtClass(
                    id = 2,
                    subjectId = 1,
                    start = LocalDateTime.of(2024, 1, 1, 11, 0),
                    end = LocalDateTime.of(2024, 1, 1, 12, 40)
                ),
            )
        ) {}
    }
}