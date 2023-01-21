package com.ivanovdev.library.db.workout

data class DbExercise(
    val id: Int,
    val name: String?,
    val duration: Long?,
    val isOwnWeight: Boolean,
    val approaches: List<DbApproach>?,
)
