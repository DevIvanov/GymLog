package com.ivanovdev.feature.screen.logger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ivanovdev.feature.screen.logger.logic.LoggerUiState
import com.ivanovdev.feature.screen.logger.logic.LoggerViewModel
import com.ivanovdev.feature.ui.common.ItemLog
import com.ivanovdev.feature.ui.theme.L
import com.ivanovdev.feature.ui.theme.PrimaryDark
import com.ivanovdev.feature.ui.theme.S
import com.ivanovdev.feature.ui.theme.TextXL

@Composable
fun LoggerScreen(
    viewModel: LoggerViewModel = hiltViewModel(),
    newLogClick: () -> Unit = {}
) {
    val uiState: LoggerUiState = viewModel.uiState.collectAsState().value//collectAsStateWithLifecycle()
    LoggerScreen(uiState = uiState, newLogClick = newLogClick)
}

@Composable
fun LoggerScreen(
    uiState: LoggerUiState,
    newLogClick: () -> Unit = {}
) {
    if (uiState is LoggerUiState.Success) {
        val logList = uiState.data.collectAsState(initial = listOf())

        Column(
            modifier = Modifier
                .background(PrimaryDark)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Logger",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = TextXL
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = L)
                    .fillMaxWidth()
                    .weight(1f)
                    .height(0.dp)
            ) {
                logList.value.forEach { log ->
                    item {
                        ItemLog(log.type, "13.01.2023", "${log.weightSum} kg")
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = S, end = S),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = newLogClick
        ){ Icon(Icons.Filled.Add,"") }
    }
}



@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    LoggerScreen()
}