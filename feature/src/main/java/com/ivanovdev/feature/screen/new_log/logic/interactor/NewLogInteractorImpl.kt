package com.ivanovdev.feature.screen.new_log.logic.interactor

import com.ivanovdev.feature.screen.new_log.logic.mapper.NewLogMapper
import com.ivanovdev.feature.screen.new_log.logic.models.UiWorkout
import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Workout
import javax.inject.Inject

class NewLogInteractorImpl @Inject constructor(
    private val repository: DBRepository,
    private val mapper: NewLogMapper
) : NewLogInteractor {

    override suspend fun insertData(item: UiWorkout) {
        repository.insert(mapper.fromUiToDomain(item))
    }

}