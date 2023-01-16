package com.ivanovdev.feature.screen.logger

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoggerViewModel : ViewModel() {

    val uiState: StateFlow<LoggerUiState> = MutableStateFlow(LoggerUiState.Success)

}