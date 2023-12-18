package xyz.miyayu.attendancereader.nfc.read

import android.nfc.Tag
import android.nfc.tech.NfcF
import android.util.Log
import xyz.miyayu.attendancereader.nfc.felica.FelicaUtil.read
import xyz.miyayu.attendancereader.nfc.model.StudentNfcCard


class MySchoolCardRead : CardReadInterface<StudentNfcCard.MySchoolCard> {
    companion object {
        private val SYSTEM_CODE = byteArrayOf(0xfe.toByte(), 0x00)
        private val SERVICE_CODE = byteArrayOf(0x1a, 0x8b.toByte())
        private val DATA_SIZE = 4
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun read(tag: Tag): StudentNfcCard.MySchoolCard {
        // カード種別の判定
        if (tag.techList.contains(NfcF::class.qualifiedName).not())
            throw IllegalStateException("Type-Fのカードではありません")
        val nfcF = NfcF.get(tag) ?: throw IllegalStateException("Type-Fとして読み込むことができませんでした")

        val studentIdData = nfcF.read(SYSTEM_CODE, DATA_SIZE, SERVICE_CODE)

        val studentId = studentIdData[0].decodeToString(2, 11)
        studentIdData.forEach {
            Log.d("NFC-F: RawData", it.joinToString(" ") { raw -> raw.toHexString() })
        }
        studentIdData.forEach {
            Log.d(
                "NFC-F: String DATA",
                String(
                    it.filter { byte -> byte != 0x00.toByte() }.toByteArray(),
                    charset("SHIFT-JIS")
                )
            )
        }

        return StudentNfcCard.MySchoolCard(studentId = studentId)
    }


}





