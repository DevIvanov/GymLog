package com.ivanovdev.library.data.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}