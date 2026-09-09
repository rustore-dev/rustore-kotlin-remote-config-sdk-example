package ru.rustore.remoteconfigexample

import androidx.compose.runtime.mutableStateListOf

fun Throwable.toLogMessage(): String =
    "${this::class.simpleName ?: this::class.java.simpleName}: ${message.orEmpty()}"

object RemoteConfigEvents {
    val events = mutableStateListOf<String>()

    fun add(message: String) {
        events.add(0, message)
        if (events.size > 100) {
            events.removeAt(events.size - 1)
        }
    }
}
