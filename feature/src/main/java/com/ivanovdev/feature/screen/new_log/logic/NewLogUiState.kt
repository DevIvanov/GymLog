package com.ivanovdev.feature.screen.new_log.logic

sealed interface NewLogUiState {
    object Loading: NewLogUiState
    object Success: NewLogUiState// data class Success( val logList = List<LogUi>)
    object Error: NewLogUiState
}