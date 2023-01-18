package com.ivanovdev.feature.screen.new_log.models

import java.time.LocalDate

sealed class NewLogEvent {
    data class ChooseDate(val newValue: LocalDate) : NewLogEvent()
    data class TypeChanged(val newValue: String) : NewLogEvent()
    object AddExerciseClicked : NewLogEvent()
    data class NameChanged(val newValue: String, val index: Int) : NewLogEvent()
    object SaveClicked : NewLogEvent()

}
