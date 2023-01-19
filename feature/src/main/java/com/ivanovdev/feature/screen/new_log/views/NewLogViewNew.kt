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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.new_log.logic.models.NewLogUiState
import com.ivanovdev.feature.screen.new_log.logic.models.UiExercise
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
                label = { Text(text = "Workout's type") }
            )
        }

        state.exercises.forEachIndexed { index, exercise ->
            item {
                ExerciseInfo(
                    exercise = exercise,
                    onDeleteClick = onDeleteClick,
                    onNameChanged = onNameChanged,
                    onWeightChanged = onWeightChanged,
                    onIterationChanged = onIterationChanged,
                    onSetsChanged = onSetsChanged,
                    isOwnWeight = isOwnWeight,
                    index = index
                )
            }
        }

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
                    text = "Add New Exercise".uppercase(),
                    color = Color.White
                )
            }
            Button(
                onClick = { onSaveClicked() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = XXL)
            ) {
                Text(
                    text = "Submit".uppercase(),
                    color = Color.White
                )
            }
        }
    }
}

//@Composable
//@Preview
//fun NewLogViewNew_Preview() {
//    NewLogViewNew(
//        padding = PaddingValues(0.dp),
//        state = NewLogUiState.New(),
//        onDateClick = {},
//        onTypeChanged = {},
//        onSaveClicked = {},
//        onNameChanged = {}
//    )
//}

@Composable
fun ExerciseInfo(
    exercise: UiExercise,
    onDeleteClick: (Int) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    onWeightChanged: (String, Int) -> Unit,
    onIterationChanged: (String, Int) -> Unit,
    onSetsChanged: (String, Int) -> Unit,
    isOwnWeight: (Boolean, Int) -> Unit,
    index: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = L)
    ) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = M)) {
            Text(
                text = stringResource(id = R.string.exercise, index + 1),
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.Delete,
                modifier = Modifier.clickable {
                    onDeleteClick(index)
                },
                contentDescription = "Delete",
                tint = Color.White
            )
        }
        OutlinedTextField(
            value = exercise.name ?: "",
            onValueChange = { onNameChanged(it, exercise.id) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = M),
            textStyle = TextStyle(color = Color.White),
            label = { Text(text = stringResource(id = R.string.exercise_name)) }
        )
        Row(
            modifier = Modifier.padding(bottom = M),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = exercise.weight ?: "",
                onValueChange = { onWeightChanged(it, exercise.id) },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = "Weight") }
            )
            Checkbox(checked = exercise.isOwnWeight, onCheckedChange = { isOwnWeight(it, exercise.id) })
            Text(text = "Own weight", color = Color.White)
        }
        Row() {
            OutlinedTextField(
                value = exercise.iteration ?: "",
                onValueChange = { onIterationChanged(it, exercise.id) },
                modifier = Modifier
                    .weight(1 / 2f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = "Iteration") }
            )
            OutlinedTextField(
                value = exercise.quantitySet ?: "",
                onValueChange = { onSetsChanged(it, exercise.id) },
                modifier = Modifier.weight(1 / 2f),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = "Number of sets") }
            )
        }
    }
}

//@Composable
//@Preview
//fun ExerciseInfo_Preview() {
//    ExerciseInfo(
//        exercise = Exercise(
//            id = 0,
//            name = "gym",
//            weight = 60.0,
//            iteration = 10,
//            quantitySet = 4
//        ),
//        onNameChanged = { "Wf", 4 } ,
//        index = 0
//    )
//}
