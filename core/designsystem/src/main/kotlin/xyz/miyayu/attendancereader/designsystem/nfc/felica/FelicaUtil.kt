package xyz.miyayu.attendancereader.feature.settings.nfc.felica

import android.nfc.tech.NfcF
import android.util.Log
import java.io.ByteArrayOutputStream

object FelicaUtil {

    //参考: https://qiita.com/nshiba/items/38f94d61c020a17314b6

    fun NfcF.read(systemCode: ByteArray, blockSize: Int, serviceCode: ByteArray): List<ByteArray> {

        try {
            this.connect()
            val pollingRes = polling(
                nfcF = this, systemCode = systemCode
            )

            val readData = getReadWithoutEncryption(
                nfcF = this,
                idm = getTargetIdm(pollingRes),
                blockCount = blockSize,
                serviceCode = serviceCode
            )

            this.close()

            return parse(readData)
        } catch (e: Exception) {
            Log.e("Felica", null, e)
            throw Exception("データの読み込みに失敗しました")
        }
    }

    private fun polling(nfcF: NfcF, systemCode: ByteArray): ByteArray {
        val bout = ByteArrayOutputStream(100)
        bout.write(0x00) // データ長バイトのダミー
        bout.write(0x00) // コマンドコード
        bout.write(systemCode[0].toInt()) // systemCode
        bout.write(systemCode[1].toInt()) // systemCode
        bout.write(0x01) // リクエストコード
        bout.write(0x0f) // タイムスロット
        val msg = bout.toByteArray()
        msg[0] = msg.size.toByte() // 先頭１バイトはデータ長

        return nfcF.transceive(msg)
    }

    private fun getTargetIdm(pollingRes: ByteArray) = pollingRes.copyOfRange(2, 10)

    // FeliCa カードユーザーマニュアル 抜粋版 4.4.5 Read Without Encryption
    private fun getReadWithoutEncryption(
        nfcF: NfcF,
        idm: ByteArray,
        blockCount: Int,
        serviceCode: ByteArray
    ): ByteArray {


        // コマンドの書き込みを開始
        val bout = ByteArrayOutputStream(100)
        bout.write(0) // データ長バイトのダミー（後ほど編集する）
        bout.write(0x06) // コマンドコード
        bout.write(idm)
        bout.write(1) // サービス数の長さ

        // サービスコードの指定はリトルエンディアンなので、下位バイトから指定します。
        bout.write(serviceCode[1].toInt()) // サービスコード下位バイト
        bout.write(serviceCode[0].toInt()) // サービスコード上位バイト
        bout.write(blockCount) // ブロック数

        // ブロック番号の指定
        for (i in 0 until blockCount) {
            bout.write(0x80) // ブロックエレメント上位バイト 「Felicaユーザマニュアル抜粋」の4.3項参照
            bout.write(i) // ブロック番号
        }

        // 完成させ、ブロック長をヘッダにつける
        val msg = bout.toByteArray()
        msg[0] = msg.size.toByte()
        return nfcF.transceive(msg)
    }

    private fun parse(res: ByteArray): List<ByteArray> {
        // res[10] エラーコード。0x00の場合が正常
        if (res[ERROR_CODE_INDEX] != 0x00.toByte())
            throw RuntimeException("Read Without Encryption Command Error")

        // res[12] 応答ブロック数
        val size = res[NUMBER_OF_BLOCKS_INDEX].toInt()

        // res[13 + n * 16] 実データ 16(byte/ブロック)の繰り返し
        return List(size) { i ->
            val offset = DATA_START_INDEX + i * BLOCK_SIZE
            ByteArray(BLOCK_SIZE) { j ->
                res[offset + j]
            }
        }
    }

    private const val ERROR_CODE_INDEX = 10
    private const val NUMBER_OF_BLOCKS_INDEX = 12
    private const val BLOCK_SIZE = 16
    private const val DATA_START_INDEX = 13
}