package com.ivanovdev.feature.screen.logger.logic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ivanovdev.feature.screen.logger.logic.interactor.LoggerInteractor
import com.ivanovdev.feature.screen.logger.logic.models.LoggerEvent
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.library.data.base.EventHandler
import com.ivanovdev.library.domainmodel.model.Workout
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

    val data = interactor.readData().asLiveData()
    private val temporaryList: MutableList<Workout> = data.value?.toMutableList() ?: mutableListOf()

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
            is LoggerEvent.DeleteWorkoutFromList -> {
//                val temporaryList = data.value?.toMutableList()
//                temporaryList?.remove(event.item)
//                val temporaryData = MutableLiveData<List<Workout>>(temporaryList)
//                _uiState.value = currentState.copy(data = temporaryData)
                if (temporaryList.isEmpty())
                    data.value?.let { temporaryList.addAll(it) }
                temporaryList.remove(event.item)
                Timber.e("temporaryList = $temporaryList")
                val temporaryData = MutableLiveData<List<Workout>>(temporaryList)
                _uiState.value = currentState.copy(data = temporaryData)
            }
            is LoggerEvent.DeleteWorkout -> {
                deleteFromDb(event.item)
                _uiState.value = currentState.copy(data = data)
                //check
                temporaryList.clear()
                data.value?.let { temporaryList.addAll(it) }
            }
            LoggerEvent.CancelDeletion -> {
                _uiState.value = currentState.copy(data = data)
                //check
                temporaryList.clear()
                data.value?.let { temporaryList.addAll(it) }
            }
            else -> {}
        }
    }

    private fun deleteFromDb(workout: Workout) {
        Timber.d("workout = $workout")
        try {
            viewModelScope.launch(Dispatchers.IO) {
                interactor.deleteItem(workout)
            }
        } catch (e: Exception) {
            Timber.e("deleteFromDb error = $e")
        }

    }

}