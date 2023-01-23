package com.ivanovdev.feature.screen.logger.logic.models

import com.ivanovdev.library.domainmodel.model.Workout

sealed class LoggerEvent {
    object ToEmptyState : LoggerEvent()
    object ToSuccessState : LoggerEvent()
    object SwipeToRefresh : LoggerEvent()
    data class DeleteWorkout(val item: Workout) : LoggerEvent()
//    object NewWorkoutClick : LoggerEvent()
}
