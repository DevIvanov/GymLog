package com.ivanovdev.library.domainmodel.model

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    val name: String,
    val weight: Double,
    val quantitySet: Int,
    val iteration: Int
)