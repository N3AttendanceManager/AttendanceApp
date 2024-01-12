package xyz.miyayu.attendancereader.feature.classlist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ClassRoute(
    viewModel: ClassViewModel = hiltViewModel()
) {
    Text(text = viewModel.subjectId.toString())
}