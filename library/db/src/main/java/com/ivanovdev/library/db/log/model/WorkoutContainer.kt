package com.ivanovdev.library.db.log.model

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutContainer(
    val id: Int,
    val name: String,
    val weight: Double,
    val quantitySet: Int,
    val iteration: Int
)
