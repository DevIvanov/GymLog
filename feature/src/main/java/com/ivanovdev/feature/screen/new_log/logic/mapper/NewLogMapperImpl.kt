package com.ivanovdev.feature.screen.new_log.logic.mapper

import com.ivanovdev.feature.screen.new_log.logic.models.UiApproach
import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise
import com.ivanovdev.feature.screen.new_log.logic.models.UiWorkout
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
            weightSum,
            exercises.map(::fromUiToDomainExercise)
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
