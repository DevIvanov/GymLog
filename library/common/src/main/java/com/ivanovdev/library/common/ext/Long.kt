package com.ivanovdev.library.common.ext

fun Long.secondsToTime(): String {
    val hoursLong = this / 3600
    val minutesLong = (this - hoursLong * 3600) / 60

    val hours = if (hoursLong.toString().length == 1) "0$hoursLong" else hoursLong.toString()
    val minutes = if (minutesLong.toString().length == 1) "0$minutesLong" else minutesLong.toString()

    return "$hours:$minutes"
}