package com.ivanovdev.feature.screen.logger.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.screen.logger.logic.interactor.LoggerInteractor
import com.ivanovdev.feature.screen.logger.logic.models.LoggerError
import com.ivanovdev.feature.screen.logger.logic.models.LoggerEvent
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise
import com.ivanovdev.library.data.base.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoggerViewModel @Inject constructor(
    private val interactor: LoggerInteractor
): ViewModel(), EventHandler<LoggerEvent> {

    private val _uiState: MutableStateFlow<LoggerUiState> = MutableStateFlow(LoggerUiState.Loading)
    val uiState: StateFlow<LoggerUiState> get() = _uiState

    init {
        readData()
    }

    override fun obtainEvent(event: LoggerEvent) {
        when (val currentViewState = _uiState.value) {
            is LoggerUiState.Success -> reduce(event, currentViewState)
            else -> {}
        }
    }

    private fun reduce(event: LoggerEvent, currentState: LoggerUiState.Success) {
        when (event) {
            is LoggerEvent.NewWorkoutClick -> {  }
        }
    }

    private fun readData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = LoggerUiState.Success(interactor.readData())
        }
    }
}