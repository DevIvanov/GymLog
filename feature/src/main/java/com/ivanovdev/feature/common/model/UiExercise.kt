package com.ivanovdev.feature.common.model

data class UiExercise(
    val id: Int,
    var name: String? = null,
    var duration: String? = null,
    var isOwnWeight: Boolean = false,
    var approaches: MutableList<UiApproach> = mutableListOf(),
)
