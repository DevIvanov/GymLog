package com.ivanovdev.feature.common.mapper

import com.ivanovdev.feature.common.model.CommonType
import com.ivanovdev.feature.common.model.UiExercise

interface CommonMapper {
    fun fromUiToCommon(uiExercises: List<UiExercise>): List<CommonType>
}