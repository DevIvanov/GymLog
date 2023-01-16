package com.ivanovdev.gymlog.screen.new_log

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivanovdev.gymlog.screen.logger.LoggerUiState
import com.ivanovdev.gymlog.screen.logger.LoggerViewModel
import com.ivanovdev.gymlog.ui.common.TopBarSecondary
import com.ivanovdev.gymlog.ui.theme.PrimaryDark

@Composable
fun NewLogScreen(
    viewModel: NewLogViewModel = viewModel(),
    onBackClick: () -> Unit = {}
) {
    val uiState: NewLogUiState = viewModel.uiState.collectAsState().value//collectAsStateWithLifecycle()
    NewLogScreen(uiState = uiState, onBackClick = onBackClick, onProgressClick = viewModel::onProgressClick)
}

@Composable
fun NewLogScreen(
    uiState: NewLogUiState,
    onBackClick: () -> Unit = {},
    onProgressClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopBarSecondary(onBackClick = onBackClick, title = "New Log") },
        backgroundColor = PrimaryDark // Set background color to avoid the white flashing when you switch between screens
    ) { padding ->
        when (uiState) {
            is NewLogUiState.Loading -> LoadingNewLogScreen(padding) { onProgressClick() }
            is NewLogUiState.Success -> SuccessNewLogScreen(padding)
            is NewLogUiState.Error -> {}
        }
    }

}

@Composable
fun LoadingNewLogScreen(padding: PaddingValues, onClick: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(padding), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.clickable { onClick() })
    }
}

@Composable
fun SuccessNewLogScreen(padding: PaddingValues) {
    Text(
        text = "New Log Screen",
        modifier = Modifier.padding(padding),
        color = Color.White
    )
}