package com.ivanovdev.feature.screen.new_log.models

import java.time.LocalDate

sealed class NewLogEvent {
    data class ChooseDate(val newValue: LocalDate) : NewLogEvent()
    data class TypeChanged(val newValue: String) : NewLogEvent()
    data class NameChanged(val newValue: String, val index: Int) : NewLogEvent()
    data class DeleteExercise(val index: Int) : NewLogEvent()
    object AddExercise : NewLogEvent()
    object SaveClicked : NewLogEvent()

}
