package com.ivanovdev.library.db.workout

import androidx.room.*
import com.ivanovdev.library.common.C.Db.ID
import com.ivanovdev.library.common.C.Db.WORKOUT_TABLE
import com.ivanovdev.library.db.workout.converter.ApproachConverter
import com.ivanovdev.library.db.workout.converter.ExerciseConverter
import com.ivanovdev.library.db.workout.converter.LocalDateConverter
import com.ivanovdev.library.db.workout.converter.StringListConverter
import java.time.LocalDate
import java.util.*

@Entity(tableName = WORKOUT_TABLE,
    indices = [Index(value = [ID], unique = true)])
@TypeConverters(LocalDateConverter::class, ExerciseConverter::class,
    ApproachConverter::class, StringListConverter::class)
data class DbWorkout (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: LocalDate,
    val type: String,
    val comment: String?,
    val duration: Long?,
    val localPhotos: List<String>?,
    val remotePhotos: List<String>?,
    val exercises: List<DbExercise>,
    val weightSum: Double
)