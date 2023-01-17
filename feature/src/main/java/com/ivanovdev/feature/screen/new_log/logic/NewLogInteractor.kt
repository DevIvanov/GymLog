package com.ivanovdev.feature.screen.new_log.logic

import com.ivanovdev.library.domainmodel.model.Log

interface NewLogInteractor {
    suspend fun insertData(item: Log)
}