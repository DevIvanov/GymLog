package com.ivanovdev.feature.screen.logger.logic

import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoggerInteractorImpl @Inject constructor(
    private val repository: DBRepository
) : LoggerInteractor {

    override suspend fun readData(): Flow<List<Log>> = repository.readAll()

}