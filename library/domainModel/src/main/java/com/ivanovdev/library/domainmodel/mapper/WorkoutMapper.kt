package com.ivanovdev.library.domainmodel.mapper

import com.ivanovdev.library.db.workout.DbWorkout
import com.ivanovdev.library.domainmodel.model.Workout

interface WorkoutMapper {
    fun fromDomainToDb(domain: Workout) : DbWorkout
    fun fromDbToDomain(db: DbWorkout) : Workout
}