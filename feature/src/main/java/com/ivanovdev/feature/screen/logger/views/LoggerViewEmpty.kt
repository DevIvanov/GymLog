package com.ivanovdev.feature.screen.logger.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.ui.theme.S
import com.ivanovdev.feature.ui.theme.TextM
import com.ivanovdev.feature.ui.theme.TextXL
import com.ivanovdev.feature.ui.theme.XL

@Composable
fun LoggerViewEmpty(
    uiState: LoggerUiState.Empty,
    newWorkoutClick: () -> Unit = {},
    toSuccessState: () -> Unit = {}
) {
    val data = uiState.data.observeAsState()

    if (data.value.isNullOrEmpty().not()) {
        toSuccessState()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_empty_list),
            contentDescription = "Empty List",
            modifier = Modifier
                .padding(bottom = XL)
                .fillMaxWidth(0.5f)
        )
        Text(
            text = stringResource(id = R.string.empty_list_title),
            color = Color.White,
            fontSize = TextXL,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.empty_list_description),
            color = Color.White,
            fontSize = TextM,
            fontWeight = FontWeight.Light
        )
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