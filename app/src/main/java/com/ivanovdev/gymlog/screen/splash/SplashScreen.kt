package com.ivanovdev.gymlog.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ivanovdev.gymlog.ui.theme.GymLogTheme
import com.ivanovdev.gymlog.ui.theme.LightBlue

@Composable
fun SplashScreen() {
    Box(modifier = Modifier
        .background(color = LightBlue),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Hello World!")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GymLogTheme {
        SplashScreen()
    }
}