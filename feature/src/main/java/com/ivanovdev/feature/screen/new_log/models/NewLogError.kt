package com.ivanovdev.feature.screen.new_log.models

sealed class NewLogError {
    object SendingGeneric : NewLogError()
}