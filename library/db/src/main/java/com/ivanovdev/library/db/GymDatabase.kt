package com.ivanovdev.library.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ivanovdev.library.common.C.Db.GYM_DATABASE
import com.ivanovdev.library.db.log.LogDao
import com.ivanovdev.library.db.log.LogEntity

@Database(entities = [LogEntity::class], version = 1, exportSchema = false)
abstract class GymDatabase: RoomDatabase() {

    abstract fun logDao(): LogDao

    companion object {
        @Volatile
        var INSTANCE: GymDatabase? = null

        fun getDatabase(context: Context): GymDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GymDatabase::class.java,
                    GYM_DATABASE
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}