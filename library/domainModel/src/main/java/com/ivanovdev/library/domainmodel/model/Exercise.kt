package com.ivanovdev.library.domainmodel.model

data class Exercise(
    val id: Int,
    val name: String?,
    val weight: Double?,
    val isOwnWeight: Boolean,
    val quantitySet: Int?,
    val iteration: Int?
)