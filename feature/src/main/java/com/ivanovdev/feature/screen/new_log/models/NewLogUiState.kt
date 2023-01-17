package com.ivanovdev.feature.screen.new_log.models

sealed class NewLogUiState {

    data class New(
        val name: String = "",
        val date: Long = 0,
    ) : NewLogUiState()

    data class Edit(
        val name: String = "",
        val date: Long = 0, //TODO change type to date
        val sendingError: NewLogError? = null
    ) : NewLogUiState()

    object Success : NewLogUiState()

    object Error : NewLogUiState()
}