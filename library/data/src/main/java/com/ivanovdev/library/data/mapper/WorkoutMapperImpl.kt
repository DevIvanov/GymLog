package com.ivanovdev.library.data.mapper

import com.ivanovdev.library.db.workout.DbExercise
import com.ivanovdev.library.db.workout.DbWorkout
import com.ivanovdev.library.domainmodel.mapper.WorkoutMapper
import com.ivanovdev.library.domainmodel.model.Exercise
import com.ivanovdev.library.domainmodel.model.Workout
import javax.inject.Inject

class WorkoutMapperImpl @Inject constructor() : WorkoutMapper {

    override fun fromDomainToDb(domain: Workout): DbWorkout = with(domain) {
        DbWorkout(
            id,
            date,
            type,
            weightSum,
            exercises.map(::fromDomainToDbExercises)
        )
    }

    override fun fromDbToDomain(db: DbWorkout): Workout = with(db) {
        Workout(
            id,
            date,
            type,
            weightSum,
            exercises.map(::fromDbToDomainExercises)
        )
    }

    private fun fromDomainToDbExercises(domain: Exercise): DbExercise = with(domain) {
        DbExercise(
            id,
            name,
            weight,
            isOwnWeight,
            quantitySet,
            iteration
        )
    }

    private fun fromDbToDomainExercises(db: DbExercise): Exercise = with(db) {
        Exercise(
            id,
            name,
            weight,
            isOwnWeight,
            quantitySet,
            iteration
        )
    }

}