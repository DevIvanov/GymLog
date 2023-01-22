package com.ivanovdev.feature.common.model

sealed class CommonType {

    data class Exercise(
        val exerciseId: Int,
        var exerciseIndex: Int,
        var isApproachEmpty: Boolean = true,
        var name: String? = null,
        var duration: String? = null,
        var ownWeight: Boolean = false,
    ) : CommonType()

    data class Approach(
        val exerciseId: Int,
        val approachId: Int,
        var approachIndex: Int,
        var isAddButtonVisible: Boolean = false,
        var isOwnWeight: Boolean = false,
        var weight: String? = null,
        var reps: String? = null,
        var approaches: String? = null,
    ) : CommonType()

    object AddButton : CommonType()
}
