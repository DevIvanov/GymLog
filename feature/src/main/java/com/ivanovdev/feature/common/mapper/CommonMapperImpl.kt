package com.ivanovdev.feature.common.mapper

import com.ivanovdev.feature.common.model.CommonType
import com.ivanovdev.feature.common.model.UiExercise

class CommonMapperImpl: CommonMapper {

    override fun fromUiToCommon(uiExercises: List<UiExercise>): List<CommonType> {
        val list = mutableListOf<CommonType>()

        uiExercises.forEachIndexed { index, exercise ->
            list.add(
                CommonType.Exercise(
                exercise.id,
                index,
                exercise.approaches.isEmpty(),
                exercise.name,
                exercise.duration,
                exercise.isOwnWeight
            ))
            exercise.approaches.forEachIndexed { approachIndex, approach ->
                list.add(
                    CommonType.Approach(
                    exercise.id,
                    approach.id,
                    approachIndex,
                    exercise.approaches.size - 1 == approachIndex,
                    exercise.isOwnWeight,
                    approach.weight,
                    approach.reps,
                    approach.approaches
                ))
            }
        }

        list.add(CommonType.AddButton)

        return list
    }

}