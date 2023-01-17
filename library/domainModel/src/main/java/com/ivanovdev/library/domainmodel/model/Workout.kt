package com.ivanovdev.library.domainmodel.model

import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: Int,
    val name: String,
    val weight: Double,
    val quantitySet: Int,
    val iteration: Int
)