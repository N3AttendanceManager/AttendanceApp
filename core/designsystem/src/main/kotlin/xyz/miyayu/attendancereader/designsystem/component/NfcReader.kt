package xyz.miyayu.attendancereader.designsystem.component

import android.nfc.NfcAdapter
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun RememberNfcRead(
    onRead: (String) -> Unit,
) {
    //各種コンテキスト類の用意
    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current
    val activity = (context as ComponentActivity)
    val nfcAdapter by lazy {
        NfcAdapter.getDefaultAdapter(context)
    }

    val adapterCallBack = NfcAdapter.ReaderCallback { tag ->
        Log.d("NfcReader",tag.id.toHexString())
        val techList = tag.techList
        val informationList = mutableMapOf<String, String>()
        informationList["techList"] = techList.joinToString(",")
        onRead.invoke(tag.id.toHexString())
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    nfcAdapter.enableReaderMode(
                        activity,
                        adapterCallBack,
                        NfcAdapter.FLAG_READER_NFC_F,
                        null
                    )
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
}