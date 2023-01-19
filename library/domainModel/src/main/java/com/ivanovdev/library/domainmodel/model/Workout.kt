package com.ivanovdev.library.domainmodel.model

import java.time.LocalDate

data class Workout(
    val id: Int,
    val date: LocalDate,
    val type: String,
    val weightSum: Double?,
    val exercises: List<Exercise>
)
