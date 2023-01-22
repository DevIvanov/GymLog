package com.ivanovdev.feature.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.common.model.UiWorkout
import com.ivanovdev.feature.ui.theme.*
import com.ivanovdev.library.common.ext.toStringDate
import com.ivanovdev.library.domainmodel.model.Workout
import timber.log.Timber

@Composable
fun ItemLog(workout: Workout, image: Int = R.drawable.placeholder) {
    Timber.e("workout = $workout")
    Box(modifier = Modifier
        .padding(horizontal = S, vertical = XS)
        .height(200.dp)
        .background(color = PrimaryLight, shape = RoundedCornerShape(S))
    ) {
        Column(Modifier.padding(S)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "photo",
                    modifier = Modifier
                        .height(ImageSize)
                        .width(ImageSize),
                    contentScale = ContentScale.Inside,
                    colorFilter = ColorFilter.tint(Color.White)
                )
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = M),
                    text = workout.type,
                    fontSize = TextXL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
                Text(
                    text = workout.date.toStringDate(),
                    fontSize = TextM,
                    fontFamily = FontFamily.Default,
                    color = GreyLightText
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = M),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Weight",
                    fontSize = TextXL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
                Text(
                    text = "${workout.weightSum} kg",
                    fontSize = TextM,
                    fontFamily = FontFamily.Default,
                    color = GreyLightText
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "More info ...",
                textAlign = TextAlign.End
            )
        }
    }
}