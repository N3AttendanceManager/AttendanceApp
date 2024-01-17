package xyz.miyayu.attendancereader.classdetail

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import xyz.miyayu.attendancereader.designsystem.component.RememberNfcRead

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ClassReadRoute(
    viewModel: ClassDetailViewModel
) {
    val resources by viewModel.attendanceResources.collectAsState()
    val classificationsList = resources?.classifications ?: emptyList()
    var selectedChip by remember { mutableIntStateOf(0) }

    RememberNfcRead(onRead = {
        viewModel.onCardScanned(
            idm = it,
            classifications = classificationsList[selectedChip]
        )
    })
    Row {
        classificationsList.forEachIndexed { index, classifications ->
            InputChip(
                selected = index == selectedChip,
                onClick = { selectedChip = index },
                label = { Text(text = classifications.name) }
            )
        }
    }
}