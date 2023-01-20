package com.ivanovdev.feature.screen.new_log.logic.models

data class UiExercise(
    val id: Int,
    var name: String? = null,
    var duration: String? = null,
    var isOwnWeight: Boolean = false,
    var approaches: MutableList<UiApproach> = mutableListOf(),
)
