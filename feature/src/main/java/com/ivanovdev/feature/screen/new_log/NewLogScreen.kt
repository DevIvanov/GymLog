package com.ivanovdev.feature.screen.new_log

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivanovdev.feature.screen.new_log.logic.NewLogUiState
import com.ivanovdev.feature.screen.new_log.logic.NewLogViewModel
import com.ivanovdev.feature.ui.common.TopBarSecondary
import com.ivanovdev.feature.ui.theme.PrimaryDark

@Composable
fun NewLogScreen(
    viewModel: NewLogViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    val uiState: NewLogUiState = viewModel.uiState.collectAsState().value//collectAsStateWithLifecycle()
    NewLogScreen(uiState = uiState, onBackClick = onBackClick,
        onProgressClick = viewModel::onProgressClick, submitClick = viewModel::submitClick)
}

@Composable
fun NewLogScreen(
    uiState: NewLogUiState,
    onBackClick: () -> Unit = {},
    onProgressClick: () -> Unit = {},
    submitClick: (String) -> Unit = {}
) {
    Scaffold(
        topBar = { TopBarSecondary(onBackClick = onBackClick, title = "New Log") },
        backgroundColor = PrimaryDark // Set background color to avoid the white flashing when you switch between screens
    ) { padding ->
        when (uiState) {
            is NewLogUiState.Loading -> LoadingNewLogScreen(padding) { onProgressClick() }
            is NewLogUiState.Success -> SuccessNewLogScreen(padding, submitClick)
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
fun SuccessNewLogScreen(
    padding: PaddingValues,
    submitClick: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    Column() {
        Text(
            text = "New Log Screen",
            modifier = Modifier.padding(padding),
            color = Color.White
        )
        TextField(value = name, onValueChange = { name = it })
        Button(onClick = { submitClick(name) }) {
            Text(text = "Submit")
        }
    }
}