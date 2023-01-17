package com.ivanovdev.library.db.workout

import androidx.room.*
import com.ivanovdev.library.common.C.Db.READ_QUERY
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query(READ_QUERY)
    fun readAllData(): Flow<List<WorkoutEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: WorkoutEntity)

    @Update
    fun update(item: WorkoutEntity)

    @Delete
    fun delete(item: WorkoutEntity)

    @Update(entity = WorkoutEntity::class)
    fun updateValues(list: List<WorkoutEntity>)
}