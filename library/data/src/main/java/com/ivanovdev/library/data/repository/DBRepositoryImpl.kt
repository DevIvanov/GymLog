package com.ivanovdev.library.data.repository

import com.ivanovdev.library.db.log.LogDao
import com.ivanovdev.library.domainmodel.mapper.LogMapper
import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val logDao: LogDao,
    private val mapper: LogMapper
) : DBRepository {

    override fun readAll(): Flow<List<Log>> {
        return logDao.readAllData().map { it.map(mapper::fromDbToDomain) }
    }

    override suspend fun insert(item: Log) {
        return logDao.insert(mapper.fromDomainToDb(item))
    }

    override suspend fun update(item: Log) {
        return logDao.update(mapper.fromDomainToDb(item))
    }

    override suspend fun delete(item: Log) {
        return logDao.delete(mapper.fromDomainToDb(item))
    }

}