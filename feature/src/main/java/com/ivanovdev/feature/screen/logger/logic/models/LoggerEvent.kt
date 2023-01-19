package com.ivanovdev.feature.screen.logger.logic.models

sealed class LoggerEvent {
    object ToEmptyState : LoggerEvent()
    object ToSuccessState : LoggerEvent()
    object SwipeToRefresh : LoggerEvent()
    data class DeleteWorkout(val index: Int) : LoggerEvent()
//    object NewWorkoutClick : LoggerEvent()
}
