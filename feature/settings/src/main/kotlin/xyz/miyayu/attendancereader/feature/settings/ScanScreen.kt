package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import xyz.miyayu.attendancereader.designsystem.component.RememberNfcRead

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ScanRoute(
    viewModel: SettingViewModel,
    onScanned: () -> Unit,
) {
    LaunchedEffect(Unit){
        viewModel.uiEvents.collect {uiEvents ->
            uiEvents.forEach { uiEvent ->
                when(uiEvent){
                    UiEvent.IcScanned -> {
                        onScanned.invoke()
                    }
                }
                viewModel.consumeUiEvents(uiEvent)
            }

        }
    }
    RememberNfcRead(onRead = { idm ->
        viewModel.onIcFetched(
            idm = idm
        )
    })
}