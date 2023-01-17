package com.ivanovdev.library.data.mapper

import com.ivanovdev.library.db.workout.WorkoutEntity
import com.ivanovdev.library.domainmodel.mapper.WorkoutMapper
import com.ivanovdev.library.domainmodel.model.Workout
import com.ivanovdev.library.domainmodel.model.Exercise
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WorkoutMapperImpl @Inject constructor() : WorkoutMapper {

    @OptIn(ExperimentalSerializationApi::class)
    override fun fromDomainToDb(domain: Workout): WorkoutEntity = with(domain) {
        WorkoutEntity(
            id,
            date,
            type,
            weightSum,
            compressExercisesWithValues(exercises)
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun fromDbToDomain(db: WorkoutEntity): Workout = with(db) {
        Workout(
            id,
            date,
            type,
            weightSum,
            decompressExercisesWithValues(workouts)
        )
    }

    @ExperimentalSerializationApi
    private fun compressExercisesWithValues(pairs: List<Exercise>): String {
        return Json.encodeToString(pairs)
    }

    @ExperimentalSerializationApi
    private fun decompressExercisesWithValues(input: String): List<Exercise> {
        return Json.decodeFromString(input)
    }

}