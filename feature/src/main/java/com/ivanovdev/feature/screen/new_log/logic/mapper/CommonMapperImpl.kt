package com.ivanovdev.feature.screen.new_log.logic.mapper

import com.ivanovdev.feature.screen.new_log.logic.models.CommonType
import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise
import timber.log.Timber

class CommonMapperImpl: CommonMapper {

    override fun fromUiToCommon(uiExercises: List<UiExercise>): List<CommonType> {
        val list = mutableListOf<CommonType>()

        uiExercises.forEach {
            list.add(CommonType.Exercise(
                it.id,
                it.approaches.isEmpty(),
                it.name,
                it.duration,
                it.isOwnWeight
            ))
            it.approaches.forEachIndexed { index, approach ->
                list.add(CommonType.Approach(
                    it.id,
                    approach.id,
                    it.approaches.size - 1 == index,
                    approach.weight,
                    approach.reps,
                    approach.approaches
                ))
            }
        }

        list.add(CommonType.AddButton)

        Timber.e("list = $list")
        return list
    }

}