package com.ivanovdev.feature.screen.logger.logic

import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow

interface LoggerInteractor {
    suspend fun readData(): Flow<List<Log>>
}