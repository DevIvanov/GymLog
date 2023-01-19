package com.ivanovdev.feature.di

import com.ivanovdev.feature.screen.logger.logic.interactor.LoggerInteractor
import com.ivanovdev.feature.screen.logger.logic.interactor.LoggerInteractorImpl
import com.ivanovdev.library.data.repository.DBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    fun getLoggerInteractor(repository: DBRepository): LoggerInteractor =
        LoggerInteractorImpl(repository)

}