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
            exercise.approaches.forEachIndexed { index2, approach ->
                list.add(
                    CommonType.Approach(
                    exercise.id,
                    approach.id,
                    exercise.approaches.size - 1 == index2,
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