package com.ivanovdev.feature.screen.logger.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun LoggerViewLoading(
    uiState: LoggerUiState.Loading,
    toEmptyState: () -> Unit = {},
    toSuccessState: () -> Unit = {}
) {
    val data = uiState.data.observeAsState()

    if (data.value.isNullOrEmpty().not()) {
        toSuccessState()
    } else {
        LaunchedEffect(true){
            delay(1000L)
            toEmptyState()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}