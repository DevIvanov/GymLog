package com.ivanovdev.feature.common.model

import java.time.LocalDate

data class UiWorkout(
    val id: Int = 0,
    var date: LocalDate? = null,
    var type: String? = null,
    val comment: String? = null,
    val duration: Long? = null,
    var weightSum: Double? = null,
    var exercises: List<UiExercise> = listOf()
)
