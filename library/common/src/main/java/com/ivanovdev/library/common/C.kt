package com.ivanovdev.library.common

object C {

    object Db {
        const val GYM_DATABASE = "gym_database"
        const val WORKOUT_TABLE = "workout_table"
        const val ID = "id"
        const val READ_QUERY = "SELECT * FROM $WORKOUT_TABLE ORDER BY $ID ASC"
    }

}