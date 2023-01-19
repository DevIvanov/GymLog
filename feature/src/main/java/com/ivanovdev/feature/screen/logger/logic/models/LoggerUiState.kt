package com.ivanovdev.feature.screen.logger.logic.models

import androidx.lifecycle.LiveData
import com.ivanovdev.library.domainmodel.model.Workout

sealed interface LoggerUiState {
    data class Loading(val data: LiveData<List<Workout>>) : LoggerUiState
    data class Success(val data: LiveData<List<Workout>>) : LoggerUiState
    data class Empty(val data: LiveData<List<Workout>>) : LoggerUiState
    data class Error(val errorMessage: String?) : LoggerUiState
}