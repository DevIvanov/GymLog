package com.ivanovdev.feature.screen.new_log.logic.mapper

import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise
import com.ivanovdev.feature.screen.new_log.logic.models.UiWorkout
import com.ivanovdev.library.domainmodel.model.Exercise
import com.ivanovdev.library.domainmodel.model.Workout

class NewLogMapperImpl : NewLogMapper {

    //TODO check !!
    override fun fromUiToDomain(ui: UiWorkout): Workout = with(ui) {
        Workout(
            id,
            date!!,
            type!!,
            weightSum,
            exercises.map(::fromUiToDomainExercise)
        )
    }

    private fun fromUiToDomainExercise(ui: UiExercise): Exercise = with(ui) {
        Exercise(
            id,
            name,
            weight?.toDouble(),
            isOwnWeight,
            quantitySet?.toInt(),
            iteration?.toInt()
        )
    }

}
