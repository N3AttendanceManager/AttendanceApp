package xyz.miyayu.attendancereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.route.LoginRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AttendanceReaderTheme {
                Scaffold {
                    LoginRoute(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(it)
                    )
                }
            }
        }
    }
}