package com.ivanovdev.feature.screen.new_log

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivanovdev.feature.screen.new_log.logic.NewLogViewModel
import com.ivanovdev.feature.screen.new_log.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState
import com.ivanovdev.feature.screen.new_log.views.NewLogViewError
import com.ivanovdev.feature.screen.new_log.views.NewLogViewNew
import com.ivanovdev.feature.screen.new_log.views.NewLogViewSuccess
import com.ivanovdev.feature.ui.common.TopBarSecondary
import com.ivanovdev.feature.ui.theme.PrimaryDark
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewLogScreen(
    viewModel: NewLogViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState: NewLogUiState = viewModel.uiState.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    Timber.e("uiState = $uiState")

    Scaffold(
        topBar = { TopBarSecondary(onBackClick = { navController.popBackStack() }, title = "New Workout") },
        backgroundColor = PrimaryDark,
    ) { padding ->
        when (uiState) {
            is NewLogUiState.New -> NewLogViewNew(
                padding = padding,
                state = uiState,
                onDateClick = { viewModel.obtainEvent(NewLogEvent.ChooseDate(it)) },
                onTypeChanged = { viewModel.obtainEvent(NewLogEvent.TypeChanged(it)) },
                onNameChanged = { value, string ->
                    viewModel.obtainEvent(NewLogEvent.NameChanged(value, string))
                 },
                onAddExerciseClick = {
                    viewModel.obtainEvent(NewLogEvent.AddExerciseClicked)
                },
                onSaveClicked = {
                    keyboardController?.hide()
                    viewModel.obtainEvent(NewLogEvent.SaveClicked)
                },
            )
            is NewLogUiState.Edit -> NewLogViewError(
                onCloseClick = { navController.popBackStack() }
            )
            is NewLogUiState.Success -> NewLogViewSuccess(
                onCloseClick = { navController.popBackStack() }
            )
            is NewLogUiState.Error -> {}
        }
    }

}

