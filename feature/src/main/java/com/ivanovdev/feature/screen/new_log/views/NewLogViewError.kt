package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.ui.theme.*

@Composable
fun NewLogViewError(
    uiState: NewLogUiState.Error
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

//@Composable
//fun NewLogViewError(
//    modifier: Modifier = Modifier,
//    onCloseClick: () -> Unit
//) {
//    Surface(
//        modifier = modifier.fillMaxSize(),
//        color = PrimaryDark
//    ) {
//        Box {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .align(Alignment.Center),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(
//                    modifier = Modifier
//                        .size(90.dp)
//                        .align(CenterHorizontally),
//                    imageVector = Icons.Filled.CheckCircle,
//                    tint = Color.White,
//                    contentDescription = "Accepted Icon"
//                )
//
//                Text(
//                    modifier = Modifier.padding(top = 24.dp),
//                    text = "Error",
//                    color = GreyLightText
//                )
//
//                Button(
//                    modifier = Modifier
//                        .padding(top = 16.dp)
//                        .height(48.dp)
//                        .fillMaxWidth(),
//                    onClick = onCloseClick,
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Color.White,
//                        disabledBackgroundColor = Primary.copy(
//                            alpha = 0.3f
//                        )
//                    )
//                ) {
//                    Text(
//                        text = "close",
//                        color = PrimaryDark
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//@Preview
//fun NewLogViewError_Preview() {
//    NewLogViewError(onCloseClick = {})
//}