package com.ivanovdev.library.domainmodel.model

data class Exercise(
    val id: Int,
    val name: String?,
    val duration: Long?,
    val isOwnWeight: Boolean,
    val approaches: List<Approach>?
) {
    val exerciseWeight: Double = approaches?.sumOf { it.approachWeight } ?: 0.0
}