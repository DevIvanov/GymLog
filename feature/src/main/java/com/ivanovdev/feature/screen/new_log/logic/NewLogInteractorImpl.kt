package com.ivanovdev.feature.screen.new_log.logic

import com.ivanovdev.library.data.repository.DBRepository
import com.ivanovdev.library.domainmodel.model.Log
import javax.inject.Inject

class NewLogInteractorImpl @Inject constructor(
    private val repository: DBRepository
) : NewLogInteractor {

    override suspend fun insertData(item: Log) {
        repository.insert(item)
    }

}