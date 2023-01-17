package com.ivanovdev.feature.di

import com.ivanovdev.feature.screen.new_log.logic.NewLogInteractor
import com.ivanovdev.feature.screen.new_log.logic.NewLogInteractorImpl
import com.ivanovdev.library.data.repository.DBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewLogModule {

    @Provides
    fun getNewLogInteractor(repository: DBRepository): NewLogInteractor =
        NewLogInteractorImpl(repository)

}