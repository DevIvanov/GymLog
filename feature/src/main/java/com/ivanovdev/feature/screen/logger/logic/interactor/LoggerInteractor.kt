package com.ivanovdev.feature.screen.logger.logic.interactor

import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow

interface LoggerInteractor {
    fun readData(): Flow<List<Workout>>
}