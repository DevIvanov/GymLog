package com.ivanovdev.library.domainmodel.mapper

import com.ivanovdev.library.db.workout.WorkoutEntity
import com.ivanovdev.library.domainmodel.model.Workout

interface WorkoutMapper {
    fun fromDomainToDb(domain: Workout) : WorkoutEntity
    fun fromDbToDomain(db: WorkoutEntity) : Workout
}