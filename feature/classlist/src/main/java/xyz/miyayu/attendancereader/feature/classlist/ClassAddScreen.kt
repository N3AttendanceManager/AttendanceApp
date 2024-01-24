package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    val uiState by viewModel.uiState.collectAsState()

    ClassAddScreen(
        onSelectedClassTime = { classTimes ->
            viewModel.createNewClass(classTimes)
        },
        isLoading = uiState.isLoading
    )
}

/**
 * タスク：授業時間を[ClassTimes]から選択してもらい、[onSelectedClassTime]を呼び出す
 */
@Composable
private fun ClassAddScreen(
    isLoading: Boolean,
    onSelectedClassTime: (ClassTimes) -> Unit
) {
    //TODO 画面の実装をここにする！
    val selectedClassTime = remember { mutableStateOf(ClassTimes.One) }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Dropdown(selectedClassTime = selectedClassTime)

        Spacer(modifier = Modifier.padding(8.dp))

        AttendanceButton(
            onClick = { onSelectedClassTime.invoke(selectedClassTime.value) },
            text = "追加"
        )
    }
    if (isLoading) Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun Dropdown(selectedClassTime: MutableState<ClassTimes>) {
    val options = ClassTimes.entries.toTypedArray() // 時限のオプションClassTimes.values() // 時限のオプション
    val expanded = remember { mutableStateOf(false) }
    val selectedOptionText = remember { mutableStateOf(options[0]) }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .size(250.dp, 50.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(4.dp))
            .clickable { expanded.value = !expanded.value },
    ) {
        Text(
            text = selectedOptionText.value.zigen,
            modifier = Modifier.padding(start = 10.dp)
        )
        Icon(
            Icons.Filled.ArrowDropDown, "contentDescription",
            Modifier.align(Alignment.CenterEnd)
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText.value = option
                        expanded.value = false
                    },
                    text = {
                        Text(text = option.zigen)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
private fun ClassAddScreenPreview() {
    PreviewSurface {
        ClassAddScreen(
            onSelectedClassTime = {},
            isLoading = true
        )
    }
}