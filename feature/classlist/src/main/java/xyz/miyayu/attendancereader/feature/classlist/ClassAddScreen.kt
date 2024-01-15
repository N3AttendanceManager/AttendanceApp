package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.designsystem.component.AttendanceButton
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface

@Composable
internal fun ClassAddRoute(
    viewModel: ClassViewModel = hiltViewModel(),
    onCreated: () -> Unit
) {
    // UiEvent処理の振り分け
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { uiEvents ->
            uiEvents.forEach { uiEvent ->
                when (uiEvent) {
                    ClassAddUiEvents.Success -> {
                        onCreated.invoke()
                    }

                    ClassAddUiEvents.Failed -> {
                        //TODO 失敗した時の処理
                    }
                }
                viewModel.consumeUiEvents(event = uiEvent)
            }

        }
    }

    ClassAddScreen(
        onSelectedClassTime = { classTimes ->
            viewModel.createNewClass(classTimes)
        }
    )
}

/**
 * タスク：授業時間を[ClassTimes]から選択してもらい、[onSelectedClassTime]を呼び出す
 */
@Composable
private fun ClassAddScreen(
    onSelectedClassTime: (ClassTimes) -> Unit
) {
    AttendanceButton(onClick = {
        onSelectedClassTime.invoke(ClassTimes.One)
    }, text = "追加")
    //TODO 画面の実装をここにする！
}

@Preview
@Composable
private fun ClassAddScreenPreview() {
    PreviewSurface {
        ClassAddScreen(
            onSelectedClassTime = {}
        )
    }
}