package com.ivanovdev.feature.screen.logger.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.screen.new_log.logic.NewLogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggerViewModel @Inject constructor(
    private val interactor: LoggerInteractor
): ViewModel() {

    private val _uiState: MutableStateFlow<LoggerUiState> = MutableStateFlow(LoggerUiState.Loading)
    val uiState: StateFlow<LoggerUiState> get() = _uiState

    init {
        readData()
    }

    private fun readData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = LoggerUiState.Success(interactor.readData())
        }
    }

}