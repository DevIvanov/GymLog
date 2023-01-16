package com.ivanovdev.gymlog.screen.new_log

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ivanovdev.gymlog.ui.common.TopBarSecondary
import com.ivanovdev.gymlog.ui.theme.PrimaryDark

@Composable
fun NewLogScreen(
    uiState: NewLogUiState,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopBarSecondary(onBackClick = onBackClick, title = "New Log") },
        backgroundColor = PrimaryDark // Set background color to avoid the white flashing when you switch between screens
    ) { padding ->
        Text(
            text = "New Log Screen",
            modifier = Modifier.padding(padding),
            color = Color.White
        )
    }
}