package com.ivanovdev.feature.screen.logger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.ui.common.ItemLog
import com.ivanovdev.feature.ui.theme.L
import com.ivanovdev.feature.ui.theme.PrimaryDark
import com.ivanovdev.feature.ui.theme.S
import com.ivanovdev.feature.ui.theme.TextXL

@Composable
fun LoggerViewSuccess(
    uiState: LoggerUiState.Success,
    toEmptyState: () -> Unit = {},
    newWorkoutClick: () -> Unit = {},
) {
    val data = uiState.data.observeAsState()

    if (data.value.isNullOrEmpty()) {
        toEmptyState()
    }

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
            uiState.data.value?.forEach { workout ->
                item {
                    ItemLog(workout = workout)
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
            onClick = newWorkoutClick
        ){ Icon(Icons.Filled.Add,"") }
    }
}
