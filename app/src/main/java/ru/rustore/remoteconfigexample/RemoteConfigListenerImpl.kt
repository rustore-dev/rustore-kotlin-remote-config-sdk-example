package ru.rustore.remoteconfigexample

import android.util.Log
import ru.rustore.sdk.remoteconfig.RemoteConfigClientEventListener
import ru.rustore.sdk.remoteconfig.RemoteConfigException

private const val LOG_TAG = "rustore"

class RemoteConfigListenerImpl : RemoteConfigClientEventListener {

    private fun log(message: String) {
        Log.d(LOG_TAG, message)
        RemoteConfigEvents.add(message)
    }

    override fun backgroundJobErrors(exception: RemoteConfigException.BackgroundConfigUpdateError) {
        log("backgroundJobErrors: ${exception.toLogMessage()}")
    }

    override fun firstLoadComplete() {
        log("firstLoadComplete")
    }

    override fun initComplete() {
        log("initComplete")
    }

    override fun memoryCacheUpdated() {
        log("memoryCacheUpdated")
    }

    override fun persistentStorageUpdated() {
        log("persistentStorageUpdated")
    }

    override fun remoteConfigNetworkRequestFailure(throwable: Throwable) {
        log("remoteConfigNetworkRequestFailure: ${throwable.toLogMessage()}")
    }
}
