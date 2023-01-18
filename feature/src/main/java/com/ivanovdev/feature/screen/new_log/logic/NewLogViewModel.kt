package com.ivanovdev.feature.screen.new_log.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.screen.new_log.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState
import com.ivanovdev.library.data.base.EventHandler
import com.ivanovdev.library.domainmodel.model.Exercise
import com.ivanovdev.library.domainmodel.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewLogViewModel @Inject constructor(
    private val interactor: NewLogInteractor
) : ViewModel(), EventHandler<NewLogEvent> {

    private val _uiState: MutableStateFlow<NewLogUiState> = MutableStateFlow(NewLogUiState.New())
    val uiState: StateFlow<NewLogUiState> get() = _uiState

    private val _exercises: MutableStateFlow<MutableList<Exercise>> = MutableStateFlow(mutableListOf())

    override fun obtainEvent(event: NewLogEvent) {
        when (val currentViewState = _uiState.value) {
            is NewLogUiState.New -> reduce(event, currentViewState)
            else -> {}
        }
    }

    private fun reduce(event: NewLogEvent, currentState: NewLogUiState.New) {
        when (event) {
            is NewLogEvent.ChooseDate -> _uiState.value = currentState.copy(
                date = event.newValue
            )
            is NewLogEvent.TypeChanged -> {
                _uiState.value = currentState.copy(name = event.newValue)
                Timber.e("uiState.value = ${uiState.value}")
            }
            NewLogEvent.AddExerciseClicked -> {
                Timber.e("NewLogEvent.AddExerciseClicked")
                _exercises.value.add(Exercise(id = _exercises.value.size))
                _uiState.value = currentState.copy(exercises = _exercises.value)
                Timber.e("exercises.value = ${_exercises.value}")
                Timber.e("uiState.value = ${uiState.value}")
            }
            is NewLogEvent.NameChanged -> {
                Timber.e("NewLogEvent.NameChanged")
                Timber.e("event.newValue = ${event.newValue}")
                _exercises.value.find { it.id == event.index }?.name = event.newValue
                _uiState.value = currentState.copy(exercises = _exercises.value)
                Timber.e("exercises.value = ${_exercises.value}")
                Timber.e("uiState.value = ${uiState.value}")
            }

            NewLogEvent.SaveClicked -> saveWorkoutToDB(currentState)
        }
    }

    private fun saveWorkoutToDB(state: NewLogUiState.New) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                interactor.insertData(
                    Workout(
                        id = 0,
                        date = System.currentTimeMillis(),
                        type = state.name ?: "",
                        weightSum = 2300.0,
                        exercises = emptyList()
                    )
                )
                _uiState.value = NewLogUiState.Success
            } catch (e: Exception) {
                _uiState.value = NewLogUiState.Error
            }

        }
    }

}