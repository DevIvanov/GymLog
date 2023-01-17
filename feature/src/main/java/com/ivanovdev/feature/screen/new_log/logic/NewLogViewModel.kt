package com.ivanovdev.feature.screen.new_log.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.screen.new_log.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState
import com.ivanovdev.library.data.base.EventHandler
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
) : ViewModel(), EventHandler<NewLogEvent> {

    private val _uiState: MutableStateFlow<NewLogUiState> = MutableStateFlow(NewLogUiState.New())
    val uiState: StateFlow<NewLogUiState> get() = _uiState

    override fun obtainEvent(event: NewLogEvent) {
        when (val currentViewState = _uiState.value) {
            is NewLogUiState.New -> reduce(event, currentViewState)
            else -> {}
        }
    }

    private fun reduce(event: NewLogEvent, currentState: NewLogUiState.New) {
        when (event) {
            is NewLogEvent.NameChanged -> _uiState.value =
                currentState.copy(name = event.newValue)

            NewLogEvent.SaveClicked -> saveWorkoutToDB(currentState)
        }
    }

    private fun saveWorkoutToDB(state: NewLogUiState.New) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                interactor.insertData(
                    Log(
                        id = 0,
                        date = System.currentTimeMillis(),
                        type = state.name,
                        weightSum = 2300.0,
                        workouts = emptyList()
                    )
                )
                _uiState.value = NewLogUiState.Success
            } catch (e: Exception) {
                _uiState.value = NewLogUiState.Error
            }

        }
    }

}