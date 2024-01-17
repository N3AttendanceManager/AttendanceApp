package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import xyz.miyayu.attendancereader.designsystem.component.PreviewSurface
import xyz.miyayu.attendancereader.designsystem.component.RememberNfcRead

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ScanRoute(
    viewModel: SettingViewModel,
    onScanned: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.uiEvents.collect { uiEvents ->
            uiEvents.forEach { uiEvent ->
                when (uiEvent) {
                    UiEvent.IcScanned -> {
                        onScanned.invoke()
                    }
                }
                viewModel.consumeUiEvents(uiEvent)
            }

        }
    }
    ScanScreen()
    RememberNfcRead(onRead = {
        viewModel.onIcFetched(idm = it)
    })
}

@Composable
private fun ScanScreen(
) {
    Text(text = "Hello")
}

@Preview
@Composable
private fun ScanRouteScreenPreview() {
    PreviewSurface {
        ScanScreen()
    }
}