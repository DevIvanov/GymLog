package com.ivanovdev.library.common

object C {

    object Db {
        const val GYM_DATABASE = "gym_database"
        const val LOG_TABLE = "log_table"
        const val ID = "id"
        const val READ_QUERY = "SELECT * FROM $LOG_TABLE ORDER BY $ID ASC"
    }

}