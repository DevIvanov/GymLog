package com.ivanovdev.feature.screen.new_log.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.ivanovdev.feature.R
import com.ivanovdev.feature.common.model.CommonType
import com.ivanovdev.feature.common.util.WeightTransformation
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.ui.theme.L
import com.ivanovdev.feature.ui.theme.M
import com.ivanovdev.feature.ui.theme.XXL
import com.ivanovdev.library.common.ext.checkDouble
import com.ivanovdev.library.common.ext.toStringDate
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import timber.log.Timber
import java.time.LocalDate

@Composable
fun NewLogViewNew(
    padding: PaddingValues,
    state: NewLogUiState.New,
    onDateClick: (LocalDate) -> Unit,
    onTypeChanged: (String) -> Unit,
    onDeleteClick: (Int) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    onWeightChanged: (String, Int, Int) -> Unit,
    onRepsChanged: (String, Int, Int) -> Unit,
    onApproachesChanged: (String, Int, Int) -> Unit,
    isOwnWeight: (Boolean, Int) -> Unit,
    onAddClick: () -> Unit,
    onSaveClicked: () -> Unit,
    addApproach: (Int) -> Unit
) {
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true
        ),
        selection = CalendarSelection.Date(
            selectedDate = state.date
        ) { date ->
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
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = M),
                textStyle = TextStyle(color = Color.White),
                trailingIcon = trailingIconView,
            )

            OutlinedTextField(
                value = state.name ?: "",
                onValueChange = onTypeChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.workout_type)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                )
            )
        }

        state.commonList.forEach { common ->
            when (common) {
                is CommonType.Exercise -> {
                    item {
                        ExerciseInfoItem(
                            exerciseId = common.exerciseId,
                            exerciseIndex = common.exerciseIndex,
                            isApproachEmpty = common.isApproachEmpty,
                            duration = common.duration,
                            name = common.name,
                            ownWeight = common.ownWeight,
                            onDeleteClick = onDeleteClick,
                            onNameChanged = onNameChanged,
                            isOwnWeight = isOwnWeight,
                            addApproach = addApproach,
                        )
                    }
                }
                is CommonType.Approach -> {
                    item {
                        ApproachItem(
                            exerciseId = common.exerciseId,
                            approachId = common.approachId,
                            isAddButtonVisible = common.isAddButtonVisible,
                            isOwnWeight = common.isOwnWeight,
                            weight = common.weight,
                            reps = common.reps,
                            approaches = common.approaches,
                            onWeightChanged = onWeightChanged,
                            onIterationChanged = onRepsChanged,
                            onSetsChanged = onApproachesChanged,
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
    exerciseIndex: Int,
    duration: String?,
    name: String?,
    ownWeight: Boolean,
    onDeleteClick: (Int) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    isOwnWeight: (Boolean, Int) -> Unit,
    addApproach: (Int) -> Unit,
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
            label = { Text(text = stringResource(id = R.string.exercise_name)) },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            )

        )
        Row(
            modifier = Modifier.fillMaxWidth(),
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
    isOwnWeight: Boolean,
    weight: String?,
    reps: String?,
    approaches: String?,
    onWeightChanged: (String, Int, Int) -> Unit,
    onIterationChanged: (String, Int, Int) -> Unit,
    onSetsChanged: (String, Int, Int) -> Unit,
    addApproach: (Int) -> Unit
) {
    val doublePattern = remember { Regex("[0-9]{0," + 6 + "}+((\\.[0-9]{0," + 2 + "})?)||(\\.)?") }
    val intPattern = remember { Regex("^\\d+\$") }
    val doubleLength = 7
    val intLength = 4

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = L),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = M)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                //TODO change 83
                value = if (isOwnWeight) 83.toString() else weight ?: "",
                onValueChange = { if (it.isEmpty() || it.matches(doublePattern)
                    && it.length <= doubleLength && it.checkDouble()
                ) {
                    Timber.e("it = $it")
                    onWeightChanged(it, exerciseId, approachId)
                } },
                readOnly = isOwnWeight,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.weight)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Decimal
                ),
                visualTransformation = WeightTransformation(" kg")
            )
        }
        Row() {
            OutlinedTextField(
                value = reps ?: "",
                onValueChange = { if (it.isEmpty() || it.matches(intPattern) && it.length <= intLength) {
                    onIterationChanged(it, exerciseId, approachId)
                } },
                modifier = Modifier
                    .weight(1 / 2f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.reps)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )
            OutlinedTextField(
                value = approaches ?: "",
                onValueChange = { if (it.isEmpty() || it.matches(intPattern) && it.length <= intLength) {
                    onSetsChanged(it, exerciseId, approachId)
                } },
                modifier = Modifier.weight(1 / 2f),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = stringResource(id = R.string.approaches)) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number,
                ),
            )
        }
        if (isAddButtonVisible) {
            Text(
                modifier = Modifier
                    .padding(top = L)
                    .clickable { addApproach(exerciseId) },
                text = "Add next approach",
                color = Color.White,
            )
        }
    }
}