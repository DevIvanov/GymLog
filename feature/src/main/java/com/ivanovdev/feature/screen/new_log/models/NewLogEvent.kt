package com.ivanovdev.feature.screen.new_log.models

sealed class NewLogEvent {
    data class NameChanged(val newValue: String) : NewLogEvent()
    object SaveClicked : NewLogEvent()
}
