package com.ivanovdev.feature.screen.new_log.logic

import com.ivanovdev.library.domainmodel.model.Workout

interface NewLogInteractor {
    suspend fun insertData(item: Workout)
}