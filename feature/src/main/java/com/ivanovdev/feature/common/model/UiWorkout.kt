package com.ivanovdev.feature.common.model

import java.time.LocalDate

data class UiWorkout(
    val id: Int = 0,
    var date: LocalDate? = null,
    var type: String? = null,
    var comment: String? = null,
    var duration: Long? = null,
    var weightSum: Double? = null,
    var localPhotos: List<String>? = null,
    var remotePhotos: List<String>? = null,
    var exercises: List<UiExercise> = listOf()
)
