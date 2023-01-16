package com.ivanovdev.feature.screen.new_log

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NewLogViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<NewLogUiState> = MutableStateFlow(NewLogUiState.Loading)
    val uiState: StateFlow<NewLogUiState> get() = _uiState

    fun onProgressClick() {
        _uiState.value = NewLogUiState.Success
    }

}