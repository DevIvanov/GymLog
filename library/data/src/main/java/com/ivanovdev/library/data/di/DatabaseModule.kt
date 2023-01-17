package com.ivanovdev.library.data.di

import android.content.Context
import com.ivanovdev.library.data.mapper.WorkoutMapperImpl
import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.data.repository.DBRepositoryImpl
import com.ivanovdev.library.db.GymDatabase
import com.ivanovdev.library.db.workout.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun getRoomDbInstance(@ApplicationContext context: Context): GymDatabase {
        return GymDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getWorkoutDao(roomDatabase: GymDatabase): WorkoutDao {
        return roomDatabase.workoutDao()
    }

    @Provides
    fun getGymDBRepository(
        roomDatabase: GymDatabase,
        mapperImpl: WorkoutMapperImpl
    ): DBRepository {
        return DBRepositoryImpl(roomDatabase.workoutDao(), mapperImpl)
    }

}