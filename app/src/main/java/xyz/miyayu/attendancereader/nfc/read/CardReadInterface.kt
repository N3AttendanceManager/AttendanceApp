package xyz.miyayu.attendancereader.nfc.read

import android.nfc.Tag
import xyz.miyayu.attendancereader.nfc.model.StudentNfcCard

interface CardReadInterface<out T : StudentNfcCard> {
    fun read(tag: Tag): T
}