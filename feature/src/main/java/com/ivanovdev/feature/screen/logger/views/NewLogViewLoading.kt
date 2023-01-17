package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NewLogViewLoading(padding: PaddingValues) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(padding), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}