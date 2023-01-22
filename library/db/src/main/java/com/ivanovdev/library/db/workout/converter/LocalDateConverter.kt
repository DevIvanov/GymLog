package com.ivanovdev.library.db.workout.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LocalDateConverter {

    @TypeConverter
    fun dateToLong(date: LocalDate): Long =
        date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()

    @TypeConverter
    fun longToDate(seconds: Long): LocalDate =
        Instant.ofEpochMilli(seconds * 1000)
            .atZone(ZoneId.systemDefault()).toLocalDate()

}