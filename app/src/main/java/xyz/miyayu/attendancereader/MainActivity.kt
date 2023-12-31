package xyz.miyayu.attendancereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.imePadding
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.route.LoginRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceReaderTheme {
                LoginRoute(modifier = Modifier.imePadding())
            }
        }
    }
}