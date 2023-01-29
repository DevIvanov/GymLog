package com.ivanovdev.feature.screen.logger

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivanovdev.feature.screen.logger.logic.LoggerViewModel
import com.ivanovdev.feature.screen.logger.logic.models.LoggerEvent
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.screen.logger.views.LoggerViewEmpty
import com.ivanovdev.feature.screen.logger.views.LoggerViewError
import com.ivanovdev.feature.screen.logger.views.LoggerViewLoading
import com.ivanovdev.feature.screen.logger.views.LoggerViewSuccess
import timber.log.Timber

@Composable
fun LoggerScreen(
    viewModel: LoggerViewModel = hiltViewModel(),
) {
    val uiState= viewModel.uiState.collectAsState()
//    Timber.e("LoggerScreen = ${uiState.value}")

    when (val state = uiState.value) {
        is LoggerUiState.Loading -> LoggerViewLoading(
            uiState = state,
            toEmptyState = { viewModel.obtainEvent(LoggerEvent.ToEmptyState) },
            toSuccessState = { viewModel.obtainEvent(LoggerEvent.ToSuccessState) }
        )
        is LoggerUiState.Success -> LoggerViewSuccess(
            uiState = state,
            toEmptyState = { viewModel.obtainEvent(LoggerEvent.ToEmptyState) },
            deleteItem = { viewModel.obtainEvent(LoggerEvent.DeleteWorkout(it)) },
            deleteItemFromList = { viewModel.obtainEvent(LoggerEvent.DeleteWorkoutFromList(it)) },
            cancelDeletion = { viewModel.obtainEvent(LoggerEvent.CancelDeletion) }
        )
        is LoggerUiState.Empty -> LoggerViewEmpty(
            uiState = state,
            toSuccessState = { viewModel.obtainEvent(LoggerEvent.ToSuccessState) }
        )
        is LoggerUiState.Error -> LoggerViewError(uiState = state)
    }
}
