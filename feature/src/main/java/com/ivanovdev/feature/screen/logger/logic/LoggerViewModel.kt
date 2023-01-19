package com.ivanovdev.feature.screen.logger.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ivanovdev.feature.screen.logger.logic.interactor.LoggerInteractor
import com.ivanovdev.feature.screen.logger.logic.models.LoggerEvent
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.library.data.base.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoggerViewModel @Inject constructor(
    private val interactor: LoggerInteractor
): ViewModel(), EventHandler<LoggerEvent> {

    val data = interactor.readData().asLiveData()

    private val _uiState: MutableStateFlow<LoggerUiState> =
        MutableStateFlow(LoggerUiState.Loading(data))
    val uiState: StateFlow<LoggerUiState> get() = _uiState

    override fun obtainEvent(event: LoggerEvent) {
        when (val currentViewState = _uiState.value) {
            is LoggerUiState.Loading -> reduce(event, currentViewState)
            is LoggerUiState.Empty -> reduce(event, currentViewState)
            is LoggerUiState.Success -> reduce(event, currentViewState)
            else -> {}
        }
    }

    private fun reduce(event: LoggerEvent, currentState: LoggerUiState.Loading) {
        when (event) {
            is LoggerEvent.ToEmptyState -> _uiState.value = LoggerUiState.Empty(data)
            is LoggerEvent.ToSuccessState -> {
                _uiState.value = LoggerUiState.Success(data)
            }
            else -> {}
        }
    }

    private fun reduce(event: LoggerEvent, currentState: LoggerUiState.Empty) {
        when (event) {
            is LoggerEvent.ToSuccessState -> {
                _uiState.value = LoggerUiState.Success(data)
            }
            else -> {}
        }
    }

    private fun reduce(event: LoggerEvent, currentState: LoggerUiState.Success) {
        when (event) {
            is LoggerEvent.ToEmptyState -> {
                _uiState.value = LoggerUiState.Empty(data)
            }
            else -> {}
        }
    }


}