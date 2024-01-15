package xyz.miyayu.attendancereader.feature.classlist

enum class ClassTimes(
    val startHour: Int,
    val startMinute: Int,
    val endHour: Int,
    val endMinute: Int
) {
    One(startHour = 9, startMinute = 30, endHour = 11, endMinute = 0),
    Two(startHour = 11, startMinute = 10, endHour = 12, endMinute = 40),
}