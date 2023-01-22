package com.ivanovdev.feature.screen.new_log.logic.models

import java.time.LocalDate

sealed class NewLogEvent {
    data class ChooseDate(val newValue: LocalDate) : NewLogEvent()
    data class TypeChanged(val newValue: String) : NewLogEvent()
    data class NameChanged(val newValue: String, val id: Int) : NewLogEvent()
    data class WeightChanged(
        val newValue: String,
        val exerciseId: Int,
        val approachId: Int
    ) : NewLogEvent()
    data class RepsChanged(
        val newValue: String,
        val exerciseId: Int,
        val approachId: Int
    ) : NewLogEvent()
    data class ApproachesChanged(
        val newValue: String,
        val exerciseId: Int,
        val approachId: Int
    ) : NewLogEvent()
    data class IsOwnWeight(val newValue: Boolean, val id: Int) : NewLogEvent()
    data class DeleteExercise(val index: Int) : NewLogEvent()
    object AddExercise : NewLogEvent()
    data class AddApproach(val exerciseId: Int) : NewLogEvent()
    data class DeleteApproach(val exerciseId: Int, val index: Int) : NewLogEvent()
    object SaveClicked : NewLogEvent()

}
