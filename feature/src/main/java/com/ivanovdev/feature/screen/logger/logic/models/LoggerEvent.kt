package com.ivanovdev.feature.screen.logger.logic.models

sealed class LoggerEvent {
    object NewWorkoutClick : LoggerEvent()
}
