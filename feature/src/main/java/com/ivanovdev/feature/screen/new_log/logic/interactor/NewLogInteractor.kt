package com.ivanovdev.feature.screen.new_log.logic.interactor

import com.ivanovdev.feature.common.model.UiWorkout

interface NewLogInteractor {
    suspend fun insertData(item: UiWorkout)
}