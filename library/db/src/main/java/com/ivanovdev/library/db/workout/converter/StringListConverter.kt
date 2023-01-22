package com.ivanovdev.library.db.workout.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ivanovdev.library.db.workout.DbApproach
import com.ivanovdev.library.db.workout.DbExercise

class StringListConverter {

    @TypeConverter
    fun listToJson(value: List<String>?) = value?.let { Gson().toJson(it) }

    @TypeConverter
    fun jsonToList(value: String?) = value?.let {
        Gson().fromJson(it, Array<String>::class.java).toList()
    }

}