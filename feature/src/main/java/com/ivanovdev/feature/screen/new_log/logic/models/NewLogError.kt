package com.ivanovdev.feature.screen.new_log.logic.models

sealed class NewLogError {
    object SendingGeneric : NewLogError()
}