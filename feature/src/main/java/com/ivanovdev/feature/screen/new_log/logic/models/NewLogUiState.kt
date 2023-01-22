package com.ivanovdev.feature.screen.new_log.logic.models

import com.ivanovdev.feature.common.model.CommonType
import java.time.LocalDate

sealed interface NewLogUiState {

    data class New(
        val name: String? = null,
        val date: LocalDate = LocalDate.now(),
        val commonList: List<CommonType> = listOf(),
//        val exercises: List<UiExercise> = listOf(),
        val notifyToUpdate: Boolean = true
    ) : NewLogUiState

    data class Edit(
        val name: String? = null,
        val date: LocalDate? = null,
        val sendingError: NewLogError? = null
    ) : NewLogUiState

    object Success : NewLogUiState

    data class Error(val errorMessage: String?) : NewLogUiState
}