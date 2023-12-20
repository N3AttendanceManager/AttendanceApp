package xyz.miyayu.attendancereader

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import xyz.miyayu.attendancereader.model.credential.CredentialData
import xyz.miyayu.attendancereader.viewmodel.SettingTestViewModel

@Composable
fun SettingTestScreen(
) {
    val viewModel: SettingTestViewModel = hiltViewModel()
    var textFieldValue by remember {
        mutableStateOf("")
    }

    val state = viewModel.dataFlow.collectAsState(initial = CredentialData(jwtToken = "")).value.jwtToken
    Column {
        Text(text = state)
        TextField(value = textFieldValue, onValueChange = {textFieldValue = it})
        Button(onClick = {
            viewModel.save(textFieldValue)
        }){
            Text(text = "保存")
        }
    }


}