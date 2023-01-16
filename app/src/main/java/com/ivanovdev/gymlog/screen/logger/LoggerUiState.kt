package com.ivanovdev.gymlog.screen.logger

sealed interface LoggerUiState {
    object Success : LoggerUiState
    object Error : LoggerUiState
}