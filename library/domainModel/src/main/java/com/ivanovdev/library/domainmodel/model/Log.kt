package com.ivanovdev.library.domainmodel.model

data class Log(
    val id: Int,
    val date: Long,
    val type: String,
    val weightSum: Double,
    val workouts: List<Workout>
)
