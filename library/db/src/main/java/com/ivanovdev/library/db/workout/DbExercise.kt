package com.ivanovdev.library.db.workout

data class DbExercise(
    val id: Int,
    val name: String?,
    val weight: Double?,
    val isOwnWeight: Boolean,
    val quantitySet: Int?,
    val iteration: Int?
)
