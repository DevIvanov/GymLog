package com.ivanovdev.library.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ivanovdev.library.common.C.Db.GYM_DATABASE
import com.ivanovdev.library.db.workout.WorkoutDao
import com.ivanovdev.library.db.workout.DbWorkout

@Database(entities = [DbWorkout::class], version = 1, exportSchema = false)
abstract class GymDatabase: RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao

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