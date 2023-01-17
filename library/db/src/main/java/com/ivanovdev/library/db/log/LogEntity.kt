package com.ivanovdev.library.db.log

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ivanovdev.library.common.C.Db.ID
import com.ivanovdev.library.common.C.Db.LOG_TABLE

@Entity(tableName = LOG_TABLE,
    indices = [Index(value = [ID], unique = true)])
data class LogEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: Long,
    val type: String,
    val weightSum: Double,
    val workouts: String//List<Workout>
)