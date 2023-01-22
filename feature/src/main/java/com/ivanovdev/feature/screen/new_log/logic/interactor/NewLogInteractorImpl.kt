package com.ivanovdev.feature.screen.new_log.logic.interactor

import com.ivanovdev.feature.common.mapper.NewLogMapper
import com.ivanovdev.feature.common.model.UiWorkout
import com.ivanovdev.library.data.repository.DBRepository
import timber.log.Timber
import javax.inject.Inject

class NewLogInteractorImpl @Inject constructor(
    private val repository: DBRepository,
    private val mapper: NewLogMapper
) : NewLogInteractor {

    override suspend fun insertData(item: UiWorkout) {
        Timber.e("mapper.fromUiToDomain(item) = ${mapper.fromUiToDomain(item)}")
        try {
            repository.insert(mapper.fromUiToDomain(item))
        } catch (e: Exception) {
            Timber.e(e)
        }

    }

}