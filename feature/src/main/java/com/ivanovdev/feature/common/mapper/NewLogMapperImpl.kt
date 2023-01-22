package com.ivanovdev.feature.common.mapper

import com.ivanovdev.feature.common.model.UiApproach
import com.ivanovdev.feature.common.model.UiExercise
import com.ivanovdev.feature.common.model.UiWorkout
import com.ivanovdev.library.domainmodel.model.Approach
import com.ivanovdev.library.domainmodel.model.Exercise
import com.ivanovdev.library.domainmodel.model.Workout
import java.time.LocalDate

class NewLogMapperImpl : NewLogMapper {

    //TODO check !!
    override fun fromUiToDomain(ui: UiWorkout): Workout = with(ui) {
        Workout(
            id,
            date ?: LocalDate.now(),
            type ?: "",
            comment,
            duration,
            exercises.map(::fromUiToDomainExercise),
        )
    }

    private fun fromUiToDomainExercise(ui: UiExercise): Exercise = with(ui) {
        Exercise(
            id,
            name,
            duration?.toLong(),
            isOwnWeight,
            approaches.map(::fromUiToDomainApproach)
        )
    }

    private fun fromUiToDomainApproach(ui: UiApproach): Approach = with(ui) {
        Approach(
            id,
            weight?.toDouble(),
            approaches?.toInt(),
            reps?.toInt()
        )
    }

}
