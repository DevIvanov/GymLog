package com.ivanovdev.library.common.ext

fun Long.secondsToTime(h: String, m: String): String {
    val hoursLong = this / 3600
    val minutesLong = (this - hoursLong * 3600) / 60

    val hours = hoursLong.toString()
    val minutes = if (minutesLong.toString().length == 1 && hoursLong > 0)
        "0$minutesLong" else minutesLong.toString()

    return if (hoursLong < 1)
        "$minutes$m"
    else
        "$hours$h $minutes$m"
}