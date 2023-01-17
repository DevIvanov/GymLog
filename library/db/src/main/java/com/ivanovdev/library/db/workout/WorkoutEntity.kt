package com.ivanovdev.library.db.workout

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ivanovdev.library.common.C.Db.ID
import com.ivanovdev.library.common.C.Db.WORKOUT_TABLE

@Entity(tableName = WORKOUT_TABLE,
    indices = [Index(value = [ID], unique = true)])
data class WorkoutEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Long,
    val type: String,
    val weightSum: Double,
    val workouts: String
)