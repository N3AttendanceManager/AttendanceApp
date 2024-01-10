package xyz.miyayu.attendancereader

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import xyz.miyayu.attendancereader.designsystem.theme.AttendanceReaderTheme
import xyz.miyayu.attendancereader.login.LoginRoute

/**
 * 起動時のアクティビティ。ログイン画面
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttendanceReaderTheme {
                LoginRoute(onLoginSuccess = this::onSignIn)
            }
        }
    }

    private fun onSignIn() {
        val intent = Intent(this, TopActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(intent)
    }
}