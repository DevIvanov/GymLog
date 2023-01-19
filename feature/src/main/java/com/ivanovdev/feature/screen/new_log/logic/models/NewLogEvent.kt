package com.ivanovdev.feature.screen.new_log.logic.models

import java.time.LocalDate

sealed class NewLogEvent {
    data class ChooseDate(val newValue: LocalDate) : NewLogEvent()
    data class TypeChanged(val newValue: String) : NewLogEvent()
    data class NameChanged(val newValue: String, val id: Int) : NewLogEvent()
    data class WeightChanged(val newValue: String, val id: Int) : NewLogEvent()
    data class IterationChanged(val newValue: String, val id: Int) : NewLogEvent()
    data class SetsChanged(val newValue: String, val id: Int) : NewLogEvent()
    data class IsOwnWeight(val newValue: Boolean, val id: Int) : NewLogEvent()
    data class DeleteExercise(val index: Int) : NewLogEvent()
    object AddExercise : NewLogEvent()
    object SaveClicked : NewLogEvent()

}
