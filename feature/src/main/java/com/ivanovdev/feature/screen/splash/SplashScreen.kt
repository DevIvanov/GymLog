package com.ivanovdev.feature.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ivanovdev.feature.ui.theme.*

@Composable
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(color = PrimaryDark),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Gym\nLogger".uppercase(),
            fontSize = TextSplash,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GymLogTheme {
        SplashScreen()
    }
}