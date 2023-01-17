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
import com.ivanovdev.feature.screen.new_log.models.NewLogError
import com.ivanovdev.feature.screen.new_log.models.NewLogEvent
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState
import com.ivanovdev.feature.screen.new_log.views.NewLogViewError
import com.ivanovdev.feature.screen.new_log.views.NewLogViewLoading
import com.ivanovdev.feature.screen.new_log.views.NewLogViewNew
import com.ivanovdev.feature.screen.new_log.views.NewLogViewSuccess
import com.ivanovdev.feature.ui.common.TopBarSecondary
import com.ivanovdev.feature.ui.theme.PrimaryDark

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewLogScreen(
    viewModel: NewLogViewModel = hiltViewModel(),
    navController: NavController,
) {
    val uiState: NewLogUiState = viewModel.uiState.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = { TopBarSecondary(onBackClick = { navController.popBackStack() }, title = "New Log") },
        backgroundColor = PrimaryDark
    ) { padding ->
        when (uiState) {
            is NewLogUiState.New -> NewLogViewNew(
                padding = padding,
                state = uiState,
                onNameChanged = { viewModel.obtainEvent(NewLogEvent.NameChanged(it)) },
                onSaveClicked = {
                    keyboardController?.hide()
                    viewModel.obtainEvent(NewLogEvent.SaveClicked)
                }
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

//@ExperimentalComposeUiApi
//@ExperimentalFoundationApi
//@Composable
//fun ComposeScreen(
//    modifier: Modifier = Modifier,
//    navController: NavController,
//    composeViewModel: ComposeViewModel
//) {
//    val viewState = composeViewModel.composeViewState.observeAsState(initial = ComposeViewState.ViewStateInitial())
//    val keyboardController = LocalSoftwareKeyboardController.current
//
//    when (val state = viewState.value) {
//        is ComposeViewState.ViewStateInitial -> ComposeViewInitial(
//            modifier = modifier,
//            state = state,
//            onCheckedChange = { composeViewModel.obtainEvent(ComposeEvent.CheckboxClicked(it)) },
//            onTitleChanged = { composeViewModel.obtainEvent(ComposeEvent.TitleChanged(it)) },
//            onSaveClicked = {
//                keyboardController?.hide()
//                composeViewModel.obtainEvent(ComposeEvent.SaveClicked)
//            }
//        )
//
//        is ComposeViewState.ViewStateSuccess -> ComposeViewSuccess(
//            modifier = modifier,
//            onCloseClick = { navController.popBackStack() }
//        )
//    }
//}