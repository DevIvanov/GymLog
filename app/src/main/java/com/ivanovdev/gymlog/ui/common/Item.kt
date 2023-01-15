package com.ivanovdev.gymlog.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.ivanovdev.gymlog.ui.theme.*

@Composable
fun ItemLog(name: String, date: String, image: Int) {
    Box(modifier = Modifier.padding(horizontal = S, vertical = XS).height(200.dp)
        .background(color = PrimaryLight, shape = RoundedCornerShape(S))
    ) {
        Row(
            modifier = Modifier.padding(all = S),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "profileIcon",
                modifier = Modifier
                    .height(ImageSize)
                    .width(ImageSize)
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = S, end = S),
                text = name,
                fontSize = TextXL,
                fontFamily = FontFamily.SansSerif,
                color = BlackText
            )
            Column {
                Text(
                    text = date,
                    fontSize = TextM,
                    fontFamily = FontFamily.Default,
                    color = GreyText
                )
            }
        }
    }
}