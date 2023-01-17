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

@Composable
fun NewLogViewEdit(
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