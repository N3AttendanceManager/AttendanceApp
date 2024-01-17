package xyz.miyayu.attendancereader.classdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import xyz.miyayu.attendancereader.designsystem.R
import xyz.miyayu.attendancereader.designsystem.component.RememberNfcRead

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ClassReadRoute(
    viewModel: ClassDetailViewModel
) {
    val resources by viewModel.attendanceResources.collectAsState()
    val lastScanned by viewModel.lastScannedStudents.collectAsState()
    val lastClassification by viewModel.lastClassification.collectAsState()
    val classificationsList = resources?.classifications ?: emptyList()
    var selectedChip by remember { mutableIntStateOf(0) }


    RememberNfcRead(onRead = {
        viewModel.onCardScanned(
            idm = it,
            classifications = classificationsList[selectedChip]
        )
    })
    Column {
        Image(
            painter = painterResource(id = R.drawable.please_scan),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Row {
            classificationsList.forEachIndexed { index, classifications ->
                InputChip(
                    selected = index == selectedChip,
                    onClick = { selectedChip = index },
                    label = { Text(text = classifications.name) }
                )
            }
        }
        Text(text = "${lastScanned?.name} さんの出席を${lastClassification?.name}にしました")
    }

}