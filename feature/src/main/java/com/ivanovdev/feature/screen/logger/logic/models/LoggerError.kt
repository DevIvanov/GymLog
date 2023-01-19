package com.ivanovdev.feature.screen.logger.logic.models

sealed class LoggerError {
    object SendingGeneric : LoggerError()
}