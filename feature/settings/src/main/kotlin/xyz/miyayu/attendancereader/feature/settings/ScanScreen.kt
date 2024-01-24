package xyz.miyayu.attendancereader.feature.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.R
import xyz.miyayu.attendancereader.designsystem.component.RememberNfcRead

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ScanRoute(
    viewModel: SettingViewModel,
    onScanned: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

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
    Column {
        Image(
            painter = painterResource(id = R.drawable.please_scan),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

    RememberNfcRead(onRead = {
        viewModel.onIcFetched(idm = it)
    })
    //カードを読み取るときの処理
    if (uiState is UiState.Loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}



