package com.ivanovdev.library.data.repository

import com.ivanovdev.library.domainmodel.model.Log
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    fun readAll(): Flow<List<Log>>

    suspend fun insert(item: Log)

    suspend fun update(item: Log)

    suspend fun delete(item: Log)

}