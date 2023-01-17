package com.ivanovdev.feature.screen.logger.logic

import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow

sealed interface LoggerUiState {
    object Loading : LoggerUiState
    data class Success(val data: Flow<List<Log>>) : LoggerUiState
    object Error : LoggerUiState
}