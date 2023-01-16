package com.ivanovdev.gymlog.screen.new_log

sealed interface NewLogUiState {
    object Loading: NewLogUiState
    object Success: NewLogUiState// data class Success( val logList = List<LogUi>)
    object Error: NewLogUiState
}