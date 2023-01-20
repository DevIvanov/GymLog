package com.ivanovdev.feature.screen.new_log.logic.mapper

import com.ivanovdev.feature.screen.new_log.logic.models.CommonType
import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise

interface CommonMapper {
    fun fromUiToCommon(uiExercises: List<UiExercise>): List<CommonType>
}