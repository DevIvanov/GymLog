package com.ivanovdev.library.data.repository

import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    fun readAll(): Flow<List<Workout>>

    suspend fun insert(item: Workout)

    suspend fun update(item: Workout)

    suspend fun delete(item: Workout)

}