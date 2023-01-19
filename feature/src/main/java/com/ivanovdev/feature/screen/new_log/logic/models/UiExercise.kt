package com.ivanovdev.feature.screen.new_log.logic.models

data class UiExercise(
    val id: Int,
    var name: String? = null,
    var weight: String? = null,
    var isOwnWeight: Boolean = false,
    var quantitySet: String? = null,
    var iteration: String? = null
)
