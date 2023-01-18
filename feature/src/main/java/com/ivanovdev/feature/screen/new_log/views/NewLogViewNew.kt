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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import com.ivanovdev.feature.R
import com.ivanovdev.feature.screen.new_log.models.NewLogUiState
import com.ivanovdev.feature.ui.theme.L
import com.ivanovdev.feature.ui.theme.M
import com.ivanovdev.feature.ui.theme.XXL
import com.ivanovdev.library.common.ext.toStringDate
import com.ivanovdev.library.domainmodel.model.Exercise
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import timber.log.Timber
import java.time.LocalDate

@Composable
fun NewLogViewNew(
    padding: PaddingValues,
    state: NewLogUiState.New,
    onDateClick: (LocalDate) -> Unit,
    onTypeChanged: (String) -> Unit,
    onNameChanged: (String, Int) -> Unit,
    onAddExerciseClick: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    val calendarState = rememberSheetState()

    Timber.e("state.name = ${state.name}")
    Timber.e("state.exercises = ${state.exercises}")

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            onDateClick(date)
        }
    )

    Column(
        modifier = Modifier.padding(all = L),
//            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                .padding(top = M),
            textStyle = TextStyle(color = Color.White),
            label = { Text(text = "Workout's type") }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = M),
//                .verticalScroll(ScrollState(0), false)
        ) {
            state.exercises.forEachIndexed() { index, exercise ->
                item {
                    ExerciseInfo(
                        exercise = exercise,
                        onNameChanged = onNameChanged,
                        index = index
                    )
                }
            }
        }

        Button(
            onClick = { onAddExerciseClick() },
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
fun ExerciseInfo(exercise: Exercise, onNameChanged: (String, Int) -> Unit, index: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = L)
    ) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = M)) {
            Text(
                text = "Exercise ${index + 1}",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                Icons.Default.Delete,
                modifier = Modifier.clickable {
                    //TODO
                },
                contentDescription = "Delete",
                tint = Color.White
            )
        }

        OutlinedTextField(
            value = exercise.name ?: "",
            onValueChange = { onNameChanged(it, index) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = M),
            textStyle = TextStyle(color = Color.White),
            label = { Text(text = "Exercise Name") }
        )
        Row() {
            OutlinedTextField(
                value = exercise.weight?.toString() ?: "",
                onValueChange = { },
                modifier = Modifier
                    .weight(1 / 3f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = "Weight") }
            )
            OutlinedTextField(
                value = exercise.iteration?.toString() ?: "",
                onValueChange = { },
                modifier = Modifier
                    .weight(1 / 3f)
                    .padding(end = M),
                textStyle = TextStyle(color = Color.White),
                label = { Text(text = "Iteration") }
            )
            OutlinedTextField(
                value = exercise.quantitySet?.toString() ?: "",
                onValueChange = { },
                modifier = Modifier.weight(1 / 3f),
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
