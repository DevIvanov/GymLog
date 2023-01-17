package com.ivanovdev.library.data.di

import android.content.Context
import com.ivanovdev.library.data.mapper.LogMapperImpl
import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.data.repository.DBRepositoryImpl
import com.ivanovdev.library.db.GymDatabase
import com.ivanovdev.library.db.log.LogDao
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
    fun getLogDao(roomDatabase: GymDatabase): LogDao {
        return roomDatabase.logDao()
    }

    @Provides
    fun getGymDBRepository(
        roomDatabase: GymDatabase,
        mapperImpl: LogMapperImpl
    ): DBRepository {
        return DBRepositoryImpl(roomDatabase.logDao(), mapperImpl)
    }

}