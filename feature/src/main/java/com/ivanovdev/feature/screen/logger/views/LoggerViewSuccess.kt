package com.ivanovdev.feature.screen.logger.views

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
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
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
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

//    val list = remember {
//        mutableStateListOf<Workout>().apply {
//            uiState.data.value?.forEach { workout ->
//                add(workout)
//            }
//        }
//    }

//    Timber.e("list size = ${list.size}")
    Timber.d("uiState.data size = ${data.value?.size}")

    val scaffoldState = rememberScaffoldState()


    LaunchedEffect(key1 = null) {
        launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Deleted",
                actionLabel = "Undo"
            )
        }
    }


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

            itemsIndexed(items = uiState.data.value?.toMutableStateList()
                ?: mutableStateListOf(), key = { _, item ->
                    item.hashCode()
                }) { _, workout ->

                val state = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            deleteItem(workout)
                        }
                        true
                    }
                )

                val haptic = LocalHapticFeedback.current
                LaunchedEffect(key1 = state, block = {
                    if (state.dismissDirection != null) {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    }
                })

                SwipeToDismiss(
                    state = state,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        val direction = state.dismissDirection ?: return@SwipeToDismiss

//                        val color = when(state.dismissDirection) {
//                            DismissDirection.StartToEnd -> Color.Transparent
//                            DismissDirection.EndToStart -> Color.Red
//                            null -> PrimaryDark
//                        }
                        val color by animateColorAsState(
                            when (state.targetValue) {
                                DismissValue.Default -> Color.Transparent
                                DismissValue.DismissedToEnd -> Color.Transparent
                                DismissValue.DismissedToStart -> Color.Red
                            }
                        )
                        val alignment = when (direction) {
                            DismissDirection.StartToEnd -> Alignment.CenterStart
                            DismissDirection.EndToStart -> Alignment.CenterEnd
                        }
                        val icon = when (direction) {
                            DismissDirection.StartToEnd -> Icons.Default.Done
                            DismissDirection.EndToStart -> Icons.Default.Delete
                        }
                        val scale by animateFloatAsState(
                            if (state.targetValue == DismissValue.Default) 0.75f else 1f
                        )

                        Box(
                            Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ) {
                            Icon(
                                icon,
                                contentDescription = "Localized description",
                                modifier = Modifier.scale(scale),
                                tint = Color.White
                            )
                        }
                    },
                    dismissThresholds = { FractionalThreshold(0.3f) },
                    dismissContent = { ItemLog(workout = workout) },
                )
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

@Composable
fun DisplaySnackBar() {
    Snackbar() {
        Row() {
            Text(text = "Deleted")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "Undo".uppercase(), color = Color.Magenta)
        }

    }
}