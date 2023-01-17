package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState

@Composable
fun NewLogViewNew(
    padding: PaddingValues,
    state: NewLogUiState.New,
    onNameChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
) {
    Column() {
        Text(
            text = "New Log Screen",
            modifier = Modifier.padding(padding),
            color = Color.White
        )
        TextField(value = state.name, onValueChange = onNameChanged )
        Button(onClick = { onSaveClicked() }) {
            Text(text = "Submit")
        }
    }
}