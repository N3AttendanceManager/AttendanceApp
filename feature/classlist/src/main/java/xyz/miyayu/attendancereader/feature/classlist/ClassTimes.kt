package xyz.miyayu.attendancereader.feature.classlist

import java.time.LocalDateTime
import java.time.LocalTime

enum class ClassTimes(

    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int,
    val zigen : String
) {
    One(zigen = "一限目",startHour = 9, startMinute = 30, endHour = 11, endMinute = 0 ),
    Two( zigen = "二限目",startHour = 11, startMinute = 10, endHour = 12, endMinute = 40);

    fun getStartDateTime(): LocalTime = LocalTime.of(startHour, startMinute)
    fun getEndDateTime(): LocalTime = LocalTime.of(endHour, endMinute)
}