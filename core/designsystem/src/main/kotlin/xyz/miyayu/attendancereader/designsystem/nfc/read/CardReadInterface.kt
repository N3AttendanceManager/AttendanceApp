package xyz.miyayu.attendancereader.feature.settings.nfc.read

import android.nfc.Tag
import xyz.miyayu.attendancereader.feature.settings.nfc.model.StudentNfcCard

interface CardReadInterface<out T : StudentNfcCard> {
    fun read(tag: Tag): T
}