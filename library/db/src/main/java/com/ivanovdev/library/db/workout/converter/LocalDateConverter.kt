package com.ivanovdev.library.db.workout.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.ZoneId

class LocalDateConverter {

    @TypeConverter
    fun dateToLong(date: LocalDate): Long {
        val zoneId = ZoneId.systemDefault()
        return date.atStartOfDay(zoneId).toEpochSecond()
    }

    @TypeConverter
    fun longToDate(timestamp: Long): LocalDate =
        LocalDate.ofEpochDay(timestamp)

}