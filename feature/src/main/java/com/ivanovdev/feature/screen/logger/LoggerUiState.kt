package com.ivanovdev.feature.screen.logger

sealed interface LoggerUiState {
    object Success : LoggerUiState
    object Error : LoggerUiState
}