package com.ivanovdev.feature.screen.logger.logic.interactor

import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow

interface LoggerInteractor {
    suspend fun readData(): Flow<List<Workout>>
}