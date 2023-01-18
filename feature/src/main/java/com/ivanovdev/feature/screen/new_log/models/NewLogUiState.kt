package com.ivanovdev.feature.screen.new_log.models

import com.ivanovdev.library.domainmodel.model.Exercise
import java.time.LocalDate

sealed class NewLogUiState {

    data class New(
        val name: String? = null,
        val date: LocalDate? = null,
        val exercises: List<Exercise> = listOf()
    ) : NewLogUiState()

    data class Edit(
        val name: String? = null,
        val date: LocalDate? = null,
        val sendingError: NewLogError? = null
    ) : NewLogUiState()

    object Success : NewLogUiState()

    object Error : NewLogUiState()
}