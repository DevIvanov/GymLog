package com.ivanovdev.feature.screen.new_log.logic.models

import java.time.LocalDate

sealed class NewLogUiState {

    data class New(
        val name: String? = null,
        val date: LocalDate? = null,
        val exercises: List<UiExercise> = listOf(),
        val notifyToUpdate: Boolean = true
    ) : NewLogUiState()

    data class Edit(
        val name: String? = null,
        val date: LocalDate? = null,
        val sendingError: NewLogError? = null
    ) : NewLogUiState()

    object Success : NewLogUiState()

    object Error : NewLogUiState()
}