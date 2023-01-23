package com.ivanovdev.feature.screen.logger.logic.interactor

import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoggerInteractorImpl @Inject constructor(
    private val repository: DBRepository
) : LoggerInteractor {

    override fun readData(): Flow<List<Workout>> = repository.readAll()

    override suspend fun deleteItem(workout: Workout) {
        repository.delete(item = workout)
    }

}