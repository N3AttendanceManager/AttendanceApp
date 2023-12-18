package xyz.miyayu.attendancereader

import android.nfc.NfcAdapter
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import xyz.miyayu.attendancereader.nfc.read.MySchoolCardRead


private const val FLAGS =
    NfcAdapter.FLAG_READER_NFC_F

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun NfcReaderScreen(
) {
    //各種コンテキスト類の用意
    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current
    val activity = (context as ComponentActivity)
    val nfcAdapter by lazy {
        NfcAdapter.getDefaultAdapter(context)
    }

    //UIの状態の用意
    var contentText by remember {
        mutableStateOf("Loading...")
    }


    val adapterCallBack = NfcAdapter.ReaderCallback { tag ->
        val techList = tag.techList
        val informationList = mutableMapOf<String, String>()
        informationList["techList"] = techList.joinToString(",")
        informationList["IDm"] = tag.id.toHexString()
        informationList["読み取り結果"] = MySchoolCardRead().read(tag).toString()
        contentText = informationList.map { "${it.key}:${it.value}" }.joinToString("\n")
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    nfcAdapter.enableReaderMode(activity, adapterCallBack, FLAGS, null)
                }

                Lifecycle.Event.ON_PAUSE -> {
                    nfcAdapter.disableReaderMode(activity)
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    Text(text = contentText)
}