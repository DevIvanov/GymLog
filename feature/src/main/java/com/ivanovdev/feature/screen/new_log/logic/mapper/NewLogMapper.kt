package com.ivanovdev.feature.screen.new_log.logic.mapper

import com.ivanovdev.feature.screen.new_log.logic.models.UiWorkout
import com.ivanovdev.library.domainmodel.model.Workout

interface NewLogMapper {
    fun fromUiToDomain(ui: UiWorkout): Workout
}