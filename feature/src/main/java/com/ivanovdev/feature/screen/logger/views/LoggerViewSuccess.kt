package com.ivanovdev.feature.screen.logger.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Magenta
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
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
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun LoggerViewSuccess(
    uiState: LoggerUiState.Success,
    toEmptyState: () -> Unit,
    deleteItem: (Workout) -> Unit = {},
    deleteItemFromList: (Workout) -> Unit = {},
    cancelDeletion: () -> Unit = {}
) {
    val data = uiState.data.observeAsState()

    if (uiState.data.value.isNullOrEmpty()) {
        toEmptyState()
    }

    Timber.d("uiState.data size = ${data.value?.size}")

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            // reuse default SnackbarHost to have default animation and timing handling
            SnackbarHost(it) { data ->
                //TODO setup
                // custom snackbar with the custom colors
                Snackbar(
                    actionColor = Magenta,
                    contentColor = White,
                    backgroundColor = GreyDivider,
                    snackbarData = data
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .background(PrimaryDark)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Logger",
                fontWeight = FontWeight.Bold,
                color = White,
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
                }){ _, workout ->

                    val transitionState = remember {
                        MutableTransitionState(false).apply {
                            targetState = true
                        }
                    }

                    val state = rememberDismissState(
                        confirmStateChange = {
                            if (it == DismissValue.DismissedToStart) {
                                scope.launch {
                                    deleteItemFromList(workout)
                                    transitionState.targetState = !transitionState.targetState
                                    val snackbarResult = scaffoldState.snackbarHostState
                                        .showSnackbar(
                                            message = "Workout is deleted",
                                            actionLabel = "Undo",
                                        )
                                    when(snackbarResult) {
                                        SnackbarResult.Dismissed -> deleteItem(workout)
                                        SnackbarResult.ActionPerformed -> cancelDeletion()
                                    }
                                }
                            }
                            true
                        }
                    )

                    //TODO Check how works
                    val haptic = LocalHapticFeedback.current
                    LaunchedEffect(key1 = state, block = {
                        if (state.dismissDirection != null) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        }
                    })

                    SwipeToDismiss(
                        state = state,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(0.2f) },
                        modifier = Modifier.animateItemPlacement(),
                        dismissContent = {
                            AnimatedVisibility(
                                visibleState = transitionState,
                            ) {
                                ItemLog(workout = workout)
                            }
                        },
                        background = {
                            val direction = state.dismissDirection ?: return@SwipeToDismiss

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
                                    tint = White
                                )
                            }
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun ItemLog(workout: Workout, image: Int = R.drawable.placeholder) {
//    Timber.e("workout = $workout")
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
                        text = workout.duration?.secondsToTime(
                            stringResource(id = R.string.hours),
                            stringResource(id = R.string.minutes)
                        ) ?: "",
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