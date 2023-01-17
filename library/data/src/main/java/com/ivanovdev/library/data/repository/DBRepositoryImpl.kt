package com.ivanovdev.library.data.repository

import com.ivanovdev.library.db.workout.WorkoutDao
import com.ivanovdev.library.domainmodel.mapper.WorkoutMapper
import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val logDao: WorkoutDao,
    private val mapper: WorkoutMapper
) : DBRepository {

    override fun readAll(): Flow<List<Workout>> {
        return logDao.readAllData().map { it.map(mapper::fromDbToDomain) }
    }

    override suspend fun insert(item: Workout) {
        return logDao.insert(mapper.fromDomainToDb(item))
    }

    override suspend fun update(item: Workout) {
        return logDao.update(mapper.fromDomainToDb(item))
    }

    override suspend fun delete(item: Workout) {
        return logDao.delete(mapper.fromDomainToDb(item))
    }

}