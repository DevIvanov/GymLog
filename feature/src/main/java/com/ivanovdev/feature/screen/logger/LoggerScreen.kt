package com.ivanovdev.feature.screen.logger

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivanovdev.feature.screen.Screen
import com.ivanovdev.feature.screen.logger.logic.LoggerViewModel
import com.ivanovdev.feature.screen.logger.logic.models.LoggerEvent
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.screen.logger.views.LoggerViewSuccess

@Composable
fun LoggerScreen(
    viewModel: LoggerViewModel = hiltViewModel(),
    mainNavController: NavController,
) {
    val uiState= viewModel.uiState.collectAsState()

    when (val state = uiState.value) {
        is LoggerUiState.Loading -> {}
        is LoggerUiState.Success -> LoggerViewSuccess(
            uiState = state,
            newWorkoutClick = { mainNavController.navigate(Screen.NewLog.route) }
        )
        is LoggerUiState.Error -> {}
    }
}
