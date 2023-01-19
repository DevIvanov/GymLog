package com.ivanovdev.library.common.ext

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private const val DAY_MONTH_YEAR_PATTERN = "dd.MM.yyyy"

private val unSafeCal = Calendar.getInstance()

fun LocalDate?.toStringDate(): String =
    synchronized(unSafeCal) {
        this?.let {
            format(DateTimeFormatter.ofPattern(DAY_MONTH_YEAR_PATTERN))
        }
    } ?: ""

fun LocalDate?.toUtilDate(): Date =
    Date.from(this?.atStartOfDay(ZoneId.systemDefault())?.toInstant())


