package com.ivanovdev.library.domainmodel.model

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    val id: Int,
    var name: String? = null,
    var weight: Double? = null,
    var quantitySet: Int? = null,
    var iteration: Int? = null
)