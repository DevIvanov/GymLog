package com.ivanovdev.feature.screen.new_log.logic.models

import java.time.LocalDate

data class UiWorkout(
    val id: Int = 0,
    var date: LocalDate? = null,
    var type: String? = null,
    var weightSum: Double? = null,
    var exercises: List<UiExercise> = listOf()
)
