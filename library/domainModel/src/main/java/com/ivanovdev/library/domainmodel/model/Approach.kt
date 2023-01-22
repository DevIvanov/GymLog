package com.ivanovdev.library.domainmodel.model

data class Approach(
    val id: Int,
    val weight: Double?,
    val reps: Int?,
    val approaches: Int?,
) {
    val approachWeight: Double = (weight ?: 0.0) * (reps ?: 0) * (approaches ?: 0)
}
