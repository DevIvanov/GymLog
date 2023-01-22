package com.ivanovdev.feature.screen.new_log.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.common.model.UiApproach
import com.ivanovdev.feature.common.model.UiExercise
import com.ivanovdev.feature.common.model.UiWorkout
import com.ivanovdev.feature.screen.new_log.logic.interactor.NewLogInteractor
import com.ivanovdev.feature.common.mapper.CommonMapper
import com.ivanovdev.feature.screen.new_log.logic.models.*
import com.ivanovdev.library.data.base.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewLogViewModel @Inject constructor(
    private val interactor: NewLogInteractor,
    private val mapper: CommonMapper
) : ViewModel(), EventHandler<NewLogEvent> {

    private val _uiState: MutableStateFlow<NewLogUiState> = MutableStateFlow(NewLogUiState.New())
    val uiState: StateFlow<NewLogUiState> get() = _uiState

    private val _exercises: MutableStateFlow<MutableList<UiExercise>> = MutableStateFlow(mutableListOf())

    init {
        _uiState.value = NewLogUiState.New(
            commonList = mapper.fromUiToCommon(_exercises.value),
        )
    }

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
            is NewLogEvent.TypeChanged -> _uiState.value = currentState.copy(
                name = event.newValue
            )
            NewLogEvent.AddExercise -> {
                val id = try {
                    _exercises.value.maxBy { it.id }.id + 1
                } catch (e: Exception) {
                    Timber.e(e.message)
                    0
                }
                _exercises.value.add(UiExercise(id = id))
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.AddApproach -> {
                val id = try {
                    _exercises.value[event.exerciseId].approaches.maxBy { it.id }.id + 1
                } catch (e: Exception) {
                    Timber.e(e.message)
                    0
                }
                _exercises.value[event.exerciseId].approaches.add(UiApproach(id = id))
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.DeleteExercise -> {
                _exercises.value.removeAt(event.index)
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.NameChanged -> {
                _exercises.value.find { it.id == event.id }?.name = event.newValue
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.IsOwnWeight -> {
                _exercises.value.find { it.id == event.id }?.isOwnWeight = event.newValue
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.WeightChanged -> {
                _exercises.value.find { it.id == event.exerciseId }?.approaches
                    ?.find { it.id == event.approachId }?.weight = event.newValue
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.RepsChanged -> {
                _exercises.value.find { it.id == event.exerciseId }?.approaches
                    ?.find { it.id == event.approachId }?.reps = event.newValue
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }
            is NewLogEvent.ApproachesChanged -> {
                _exercises.value.find { it.id == event.exerciseId }?.approaches
                    ?.find { it.id == event.approachId }?.approaches = event.newValue
                val update = currentState.notifyToUpdate
                _uiState.value = currentState.copy(
                    commonList = mapper.fromUiToCommon(_exercises.value),
                    notifyToUpdate = !update
                )
            }

            NewLogEvent.SaveClicked -> saveWorkoutToDB(currentState)
        }

    }

    private fun saveWorkoutToDB(state: NewLogUiState.New) {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.e("_exercises.value = ${_exercises.value}")
            try {
                interactor.insertData(
                    UiWorkout(
                        date = state.date,
                        type = state.name,
                        weightSum = 2300.0,
                        exercises = _exercises.value //to add reverse mapper
                    )
                )
                _uiState.value = NewLogUiState.Success
            } catch (e: Exception) {
                e.printStackTrace()
                Timber.e(e.message)
                _uiState.value = NewLogUiState.Error(e.message)
            }

        }
    }

}