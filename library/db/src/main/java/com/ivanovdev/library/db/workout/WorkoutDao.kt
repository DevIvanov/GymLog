package com.ivanovdev.library.db.workout

import androidx.room.*
import com.ivanovdev.library.common.C.Db.READ_QUERY
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkoutDao {
    @Query(READ_QUERY)
    fun readAllData(): Flow<List<DbWorkout>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: DbWorkout)

    @Update
    fun update(item: DbWorkout)

    @Delete
    fun delete(item: DbWorkout)

    @Update(entity = DbWorkout::class)
    fun updateValues(list: List<DbWorkout>)
}