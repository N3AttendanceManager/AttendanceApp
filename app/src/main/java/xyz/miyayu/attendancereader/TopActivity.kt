package xyz.miyayu.attendancereader

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import xyz.miyayu.attendancereader.designsystem.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.view.NavHost

@AndroidEntryPoint
class TopActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceReaderTheme {
                NavHost(
                    onSignOut = this::onSignOut
                )
            }
        }
    }

    private fun onSignOut() {
        val intent = Intent(this, MainActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}