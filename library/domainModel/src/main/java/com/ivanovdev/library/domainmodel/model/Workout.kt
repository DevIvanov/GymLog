package com.ivanovdev.library.domainmodel.model

data class Workout(
    val id: Int,
    val date: Long,
    val type: String,
    val weightSum: Double,
    val exercises: List<Exercise>
)
