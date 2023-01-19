package com.ivanovdev.feature.di

import com.ivanovdev.feature.screen.new_log.logic.interactor.NewLogInteractor
import com.ivanovdev.feature.screen.new_log.logic.interactor.NewLogInteractorImpl
import com.ivanovdev.feature.screen.new_log.logic.mapper.NewLogMapper
import com.ivanovdev.feature.screen.new_log.logic.mapper.NewLogMapperImpl
import com.ivanovdev.library.data.repository.DBRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewLogModule {

    @Provides
    fun getNewLogMapper(): NewLogMapper = NewLogMapperImpl()

    @Provides
    fun getNewLogInteractor(repository: DBRepository, mapper: NewLogMapper): NewLogInteractor =
        NewLogInteractorImpl(repository, mapper)

}