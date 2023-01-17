package com.ivanovdev.feature.screen.new_log.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewLogViewModel @Inject constructor(
    private val interactor: NewLogInteractor
) : ViewModel() {

    private val _uiState: MutableStateFlow<NewLogUiState> = MutableStateFlow(NewLogUiState.Loading)
    val uiState: StateFlow<NewLogUiState> get() = _uiState

    fun onProgressClick() {
        _uiState.value = NewLogUiState.Success
    }

    fun submitClick(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.insertData(
                Log(
                    id = 0,
                    date = System.currentTimeMillis(),
                    type = name,
                    weightSum = 2300.0,
                    workouts = emptyList()
                )
            )
        }
    }

}