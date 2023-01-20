package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.new_log.logic.models.CommonType
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.ui.theme.L
import com.ivanovdev.feature.ui.theme.M
import com.ivanovdev.feature.ui.theme.XXL
import com.ivanovdev.library.common.ext.toStringDate
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import java.time.LocalDate

@Composable
fun NewLogViewNew(
    padding: PaddingValues,
    state: NewLogUiState.New,
    onDateClick: (LocalDate) -> Unit,
    onTypeChanged: (String) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    onWeightChanged: (String, Int) -> Unit,
    onIterationChanged: (String, Int) -> Unit,
    onSetsChanged: (String, Int) -> Unit,
    isOwnWeight: (Boolean, Int) -> Unit,
    onAddClick: () -> Unit,
    onSaveClicked: () -> Unit,
    addApproach: (Int) -> Unit
) {
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            onDateClick(date)
        }
    )

    LazyColumn(
        modifier = Modifier.padding(all = L),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "Common Info",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = M),
                color = Color.White
            )

            val trailingIconView = @Composable {
                IconButton(
                    onClick = {
                        calendarState.show()
                    },
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar),
                        contentDescription = "Calendar",
                        tint = Color.White
                    )
                }
            }

            OutlinedTextField(
                value = state.date.toStringDate(),
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = M)
                    .focusable(false, MutableInteractionSource())
                    .clickable { },
                textStyle = TextStyle(color = Color.White),
                trailingIcon = trailingIconView
            )

            OutlinedTextField(
                value = state.name ?: "",
                onValueChange = onTypeChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.workout_type)) }
            )
        }

        state.commonList.forEachIndexed { index, common ->
            when (common) {
                is CommonType.Exercise -> {
                    item {
                        ExerciseInfoItem(
                            exerciseId = common.exerciseId,
                            isApproachEmpty = common.isApproachEmpty,
                            duration = common.duration,
                            name = common.name,
                            ownWeight = common.ownWeight,
                            onDeleteClick = onDeleteClick,
                            onNameChanged = onNameChanged,
                            isOwnWeight = isOwnWeight,
                            addApproach = addApproach,
                            exerciseIndex = index,
                        )
                    }
                }
                is CommonType.Approach -> {
                    item {
                        ApproachItem(
                            exerciseId = common.exerciseId,
                            approachId = common.approachId,
                            isAddButtonVisible = common.isAddButtonVisible,
                            weight = common.weight,
                            reps = common.reps,
                            approaches = common.approaches,
                            onWeightChanged = onWeightChanged,
                            onIterationChanged = onIterationChanged,
                            onSetsChanged = onSetsChanged,
                            addApproach = addApproach
                        )
                    }
                }
                is CommonType.AddButton -> {
                    item {
                        Button(
                            onClick = { onAddClick() },
                            modifier = Modifier.padding(top = L)
                        ) {
                            Icon(
                                Icons.Default.Add,
                                modifier = Modifier.padding(end = L),
                                contentDescription = "Add",
                                tint = Color.White
                            )
                            Text(
                                text = stringResource(id = R.string.add_exercise).uppercase(),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        item {
            Button(
                onClick = { onSaveClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = XXL)
            ) {
                Text(
                    text = stringResource(id = R.string.save).uppercase(),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ExerciseInfoItem(
    isApproachEmpty: Boolean,
    exerciseId: Int,
    duration: String?,
    name: String?,
    ownWeight: Boolean,
    onDeleteClick: (Int) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    isOwnWeight: (Boolean, Int) -> Unit,
    addApproach: (Int) -> Unit,
    exerciseIndex: Int
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = L),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = M)) {
            Text(
                text = stringResource(id = R.string.exercise, exerciseIndex + 1),
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.Delete,
                modifier = Modifier.clickable {
                    onDeleteClick(exerciseIndex)
                },
                contentDescription = "Delete",
                tint = Color.White
            )
        }
        OutlinedTextField(
            value = name ?: "",
            onValueChange = { onNameChanged(it, exerciseId) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = M),
            textStyle = TextStyle(color = Color.White),
            label = { Text(text = stringResource(id = R.string.exercise_name)) }
        )
        Row(
            modifier = Modifier
                .padding(bottom = M)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Checkbox(checked = ownWeight, onCheckedChange = { isOwnWeight(it, exerciseId) })
            Text(text = stringResource(id = R.string.own_weight), color = Color.White)
        }
        if (isApproachEmpty) {
            Text(
                modifier = Modifier.clickable { addApproach(exerciseId) },
                text = "Add next approach",
                color = Color.White,
            )
        }
    }
}

@Composable
fun ApproachItem(
    exerciseId: Int,
    isAddButtonVisible: Boolean,
    approachId: Int,
    weight: String?,
    reps: String?,
    approaches: String?,
    onWeightChanged: (String, Int) -> Unit,
    onIterationChanged: (String, Int) -> Unit,
    onSetsChanged: (String, Int) -> Unit,
    addApproach: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = L),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(bottom = M).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                value = weight ?: "",
                onValueChange = { onWeightChanged(it, approachId) },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.weight)) }
            )
        }
        Row() {
            OutlinedTextField(
                value = reps ?: "",
                onValueChange = { onIterationChanged(it, approachId) },
                modifier = Modifier
                    .weight(1 / 2f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.reps)) }
            )
            OutlinedTextField(
                value = approaches ?: "",
                onValueChange = { onSetsChanged(it, approachId) },
                modifier = Modifier.weight(1 / 2f),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.approaches)) }
            )
        }
        if (isAddButtonVisible) {
            Text(
                modifier = Modifier.padding(top = L).clickable { addApproach(exerciseId) },
                text = "Add next approach",
                color = Color.White,
            )
        }
    }
}