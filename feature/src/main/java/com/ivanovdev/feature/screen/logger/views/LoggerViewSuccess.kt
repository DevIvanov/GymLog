package com.ivanovdev.feature.screen.logger.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.logger.logic.models.LoggerUiState
import com.ivanovdev.feature.ui.theme.*
import com.ivanovdev.library.common.ext.secondsToTime
import com.ivanovdev.library.common.ext.toStringDate
import com.ivanovdev.library.domainmodel.model.Workout
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoggerViewSuccess(
    uiState: LoggerUiState.Success,
    toEmptyState: () -> Unit = {},
    newWorkoutClick: () -> Unit = {},
    deleteItem: (Workout) -> Unit = {}
) {
    val data = uiState.data.observeAsState()

    if (data.value.isNullOrEmpty()) {
        toEmptyState()
    }

    Column(
        modifier = Modifier
            .background(PrimaryDark)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Logger",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = TextXL
        )
        LazyColumn(
            modifier = Modifier
                .padding(top = L)
                .fillMaxWidth()
                .weight(1f)
                .height(0.dp)
        ) {
            items(items = uiState.data.value ?: listOf(), key = null) { workout ->

                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            deleteItem(workout)
                        }
                        true
                    }
                )

                SwipeToDismiss(state = state, background = {
                    val color = when(state.dismissDirection) {
                        DismissDirection.StartToEnd -> Color.Transparent
                        DismissDirection.EndToStart -> Color.Red
                        null -> PrimaryDark
                    }
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(color = color)
                        .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                    }
                },
                dismissContent = { ItemLog(workout = workout) },
                directions = setOf(DismissDirection.EndToStart))
            }
        }
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
                    fontSize = TextL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
                Text(
                    text = workout.date.toStringDate(),
                    fontSize = TextL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = S),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.weight),
                    fontSize = TextL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
                Text(
                    text = "${workout.weightSum} ${stringResource(id = R.string.kg)}",
                    fontSize = TextL,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            if (workout.comment != null) {
                Row(
                    modifier = Modifier.padding(top = S),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.comment),
                        fontSize = TextL,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                    Text(
                        text = workout.comment.toString(),
                        fontSize = TextL,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                }
            }
            if (workout.duration != null) {
                Row(
                    modifier = Modifier.padding(top = S),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.workout_duration),
                        fontSize = TextL,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                    Text(
                        text = workout.duration?.secondsToTime() ?: "",
                        fontSize = TextL,
                        fontFamily = FontFamily.SansSerif,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "More info ...",
                textAlign = TextAlign.End
            )
        }
    }
}