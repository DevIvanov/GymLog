package com.ivanovdev.library.domainmodel.mapper

import com.ivanovdev.library.db.log.LogEntity
import com.ivanovdev.library.domainmodel.model.Log

interface LogMapper {
    fun fromDomainToDb(domain: Log) : LogEntity
    fun fromDbToDomain(db: LogEntity) : Log
}