package com.ivanovdev.library.domainmodel.model

import java.time.LocalDate

data class Workout(
    val id: Int,
    val date: LocalDate,
    val type: String,
    val comment: String?,
    val duration: Long?,
    val exercises: List<Exercise>
) {
    val weightSum: Double = exercises.sumOf { it.exerciseWeight }
}
