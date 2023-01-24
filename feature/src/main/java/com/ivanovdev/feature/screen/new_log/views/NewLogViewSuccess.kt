package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.ui.theme.GreyLightText
import com.ivanovdev.feature.ui.theme.Primary
import com.ivanovdev.feature.ui.theme.PrimaryDark
import kotlinx.coroutines.delay

@Composable
fun NewLogViewSuccess(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit
) {

    LaunchedEffect(key1 = true) {
        delay(1000L)
        onCloseClick()
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = PrimaryDark
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .align(CenterHorizontally),
                    imageVector = Icons.Filled.CheckCircle,
                    tint = Color.White,
                    contentDescription = "Accepted Icon"
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Added successfully",
                    color = GreyLightText
                )

                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = onCloseClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        disabledBackgroundColor = Primary.copy(
                            alpha = 0.3f
                        )
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.close),
                        color = PrimaryDark
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun NewLogViewSuccess_Preview() {
    NewLogViewSuccess(onCloseClick = {})
}