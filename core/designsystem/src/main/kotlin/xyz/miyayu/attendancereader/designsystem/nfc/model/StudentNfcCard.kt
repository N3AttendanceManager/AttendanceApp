package xyz.miyayu.attendancereader.feature.settings.nfc.model

sealed interface StudentNfcCard {
    sealed interface StudentNfcCardWithStudentId : StudentNfcCard

    data class MySchoolCard(val studentId: String) : StudentNfcCardWithStudentId

    data class TypeFStudentNfcCard(val idm: String) : StudentNfcCardWithStudentId
}