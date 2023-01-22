package com.ivanovdev.feature.common.mapper

import com.ivanovdev.feature.common.model.UiWorkout
import com.ivanovdev.library.domainmodel.model.Workout

interface NewLogMapper {
    fun fromUiToDomain(ui: UiWorkout): Workout
}