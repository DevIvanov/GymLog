package com.ivanovdev.feature.screen.logger.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.ui.theme.TextM
import com.ivanovdev.feature.ui.theme.TextXL

@Composable
fun LoggerViewError(
    uiState: LoggerUiState.Error
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.error),
            color = Color.White,
            fontSize = TextXL,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = uiState.errorMessage ?: stringResource(id = R.string.generic_error),
            color = Color.White,
            fontSize = TextM,
            fontWeight = FontWeight.Light
        )
    }
}