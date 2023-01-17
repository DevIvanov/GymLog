package com.ivanovdev.library.data.mapper

import com.ivanovdev.library.db.log.LogEntity
import com.ivanovdev.library.domainmodel.mapper.LogMapper
import com.ivanovdev.library.domainmodel.model.Log
import com.ivanovdev.library.domainmodel.model.Workout
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class LogMapperImpl @Inject constructor() : LogMapper {

    @OptIn(ExperimentalSerializationApi::class)
    override fun fromDomainToDb(domain: Log): LogEntity = with(domain) {
        LogEntity(
            id,
            date,
            type,
            weightSum,
            compressWorkoutsWithValues(workouts)
        )
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun fromDbToDomain(db: LogEntity): Log = with(db) {
        Log(
            id,
            date,
            type,
            weightSum,
            decompressWorkoutsWithValues(workouts)
        )
    }

    @ExperimentalSerializationApi
    private fun compressWorkoutsWithValues(pairs: List<Workout>): String {
        return Json.encodeToString(pairs)
    }

    @ExperimentalSerializationApi
    private fun decompressWorkoutsWithValues(input: String): List<Workout> {
        return Json.decodeFromString(input)
    }

}