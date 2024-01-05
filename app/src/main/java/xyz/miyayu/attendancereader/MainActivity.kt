package xyz.miyayu.attendancereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import xyz.miyayu.attendancereader.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.NavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceReaderTheme {
                NavHost()
            }
        }
    }
}