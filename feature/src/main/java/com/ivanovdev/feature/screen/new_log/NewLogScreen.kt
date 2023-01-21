package com.ivanovdev.feature.screen.new_log

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ivanovdev.feature.screen.new_log.logic.NewLogViewModel
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.screen.new_log.views.NewLogViewEdit
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
                onDeleteClick = { viewModel.obtainEvent(NewLogEvent.DeleteExercise(it)) },
                onNameChanged = { value, int ->
                    viewModel.obtainEvent(NewLogEvent.NameChanged(value, int))
                },
                isOwnWeight = { value, id ->
                    viewModel.obtainEvent(NewLogEvent.IsOwnWeight(value, id))
                },
                onWeightChanged = { value, exerciseId, approachId ->
                    viewModel.obtainEvent(NewLogEvent.WeightChanged(value, exerciseId, approachId))
                },
                onRepsChanged = { value, exerciseId, approachId ->
                    viewModel.obtainEvent(NewLogEvent.RepsChanged(value, exerciseId, approachId))
                },
                onApproachesChanged = { value, exerciseId, approachId ->
                    viewModel.obtainEvent(NewLogEvent.ApproachesChanged(value, exerciseId, approachId))
                },
                onAddClick = { viewModel.obtainEvent(NewLogEvent.AddExercise) },
                onSaveClicked = {
                    keyboardController?.hide()
                    viewModel.obtainEvent(NewLogEvent.SaveClicked)
                },
                addApproach = { viewModel.obtainEvent(NewLogEvent.AddApproach(it)) }
            )
            is NewLogUiState.Edit -> {}
//                NewLogViewEdit(
//                onCloseClick = { navController.popBackStack() }
//            )
            is NewLogUiState.Success -> NewLogViewSuccess(
                onCloseClick = { navController.popBackStack() }
            )
            is NewLogUiState.Error -> NewLogViewError(uiState = uiState)
        }
    }

}

