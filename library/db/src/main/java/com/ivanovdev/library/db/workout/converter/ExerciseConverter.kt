package com.ivanovdev.library.db.workout.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ivanovdev.library.db.workout.DbExercise

class ExerciseConverter {

    @TypeConverter
    fun listToJson(value: List<DbExercise>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<DbExercise>::class.java).toList()

}