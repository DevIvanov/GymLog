package com.ivanovdev.library.db.log

import androidx.room.*
import com.ivanovdev.library.common.C.Db.READ_QUERY
import kotlinx.coroutines.flow.Flow

@Dao
interface LogDao {
    @Query(READ_QUERY)
    fun readAllData(): Flow<List<LogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: LogEntity)

    @Update
    fun update(item: LogEntity)

    @Delete
    fun delete(item: LogEntity)

    @Update(entity = LogEntity::class)
    fun updateValues(list: List<LogEntity>)
}