package com.ivanovdev.feature.screen.logger.logic.models

import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow

sealed interface LoggerUiState {
    object Loading : LoggerUiState
    data class Success(val data: Flow<List<Workout>>) : LoggerUiState
    object Error : LoggerUiState
}