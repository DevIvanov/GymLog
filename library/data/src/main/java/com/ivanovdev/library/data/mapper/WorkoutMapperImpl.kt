package com.ivanovdev.library.data.mapper

import com.ivanovdev.library.db.workout.DbApproach
import com.ivanovdev.library.db.workout.DbExercise
import com.ivanovdev.library.db.workout.DbWorkout
import com.ivanovdev.library.domainmodel.mapper.WorkoutMapper
import com.ivanovdev.library.domainmodel.model.Approach
import com.ivanovdev.library.domainmodel.model.Exercise
import com.ivanovdev.library.domainmodel.model.Workout
import javax.inject.Inject

class WorkoutMapperImpl @Inject constructor() : WorkoutMapper {

    override fun fromDomainToDb(domain: Workout): DbWorkout = with(domain) {
        DbWorkout(
            id,
            date,
            type,
            comment,
            duration,
            exercises.map(::fromDomainToDbExercise),
            weightSum
        )
    }

    override fun fromDbToDomain(db: DbWorkout): Workout = with(db) {
        Workout(
            id,
            date,
            type,
            comment,
            duration,
            exercises.map(::fromDbToDomainExercise)
        )
    }

    private fun fromDomainToDbExercise(domain: Exercise): DbExercise = with(domain) {
        DbExercise(
            id,
            name,
            duration,
            isOwnWeight,
            approaches?.map(::fromDomainToDbApproach),
            exerciseWeight
        )
    }

    private fun fromDbToDomainExercise(db: DbExercise): Exercise = with(db) {
        Exercise(
            id,
            name,
            duration,
            isOwnWeight,
            approaches?.map(::fromDbToDomainApproach)
        )
    }

    private fun fromDomainToDbApproach(domain: Approach): DbApproach = with(domain) {
        DbApproach(
            id,
            weight,
            reps,
            approaches,
            approachWeight
        )
    }

    private fun fromDbToDomainApproach(db: DbApproach): Approach = with(db) {
        Approach(
            id,
            weight,
            reps,
            approaches
        )
    }

}