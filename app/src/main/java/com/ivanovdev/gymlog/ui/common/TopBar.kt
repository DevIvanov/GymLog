package com.ivanovdev.gymlog.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.ivanovdev.gymlog.R
import com.ivanovdev.gymlog.ui.theme.M
import com.ivanovdev.gymlog.ui.theme.Primary

@Composable
fun TopBarSecondary(
    onBackClick: () -> Unit = {},
    isBack: Boolean = true,
    title: String
) {
    TopAppBar(
        title = {
            if (isBack) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.clickable { onBackClick() }
                )
            }
            Text(
                text = title, fontSize = 18.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = M)
            )
        },
        backgroundColor = Primary,
        contentColor = Color.White
    )
}