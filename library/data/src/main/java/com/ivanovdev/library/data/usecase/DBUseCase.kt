package com.ivanovdev.library.data.usecase

import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DBUseCase @Inject constructor(private val repository: DBRepository) {

    fun readAll(): Flow<List<Log>> {
        return repository.readAll()
    }

    suspend fun insert(item: Log) {
        repository.insert(item)
    }

    suspend fun update(item: Log) {
        repository.update(item)
    }

    suspend fun delete(item: Log) {
        repository.delete(item)
    }

}