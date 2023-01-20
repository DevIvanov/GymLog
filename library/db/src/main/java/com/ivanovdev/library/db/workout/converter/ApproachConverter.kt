package com.ivanovdev.library.db.workout.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ivanovdev.library.db.workout.DbApproach
import com.ivanovdev.library.db.workout.DbExercise

class ApproachConverter {

    @TypeConverter
    fun listToJson(value: List<DbApproach>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<DbApproach>::class.java).toList()

}