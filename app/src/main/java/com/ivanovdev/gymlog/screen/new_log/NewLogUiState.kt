package com.ivanovdev.gymlog.screen.new_log

sealed interface NewLogUiState {
    object Success: NewLogUiState
    object Error: NewLogUiState
}