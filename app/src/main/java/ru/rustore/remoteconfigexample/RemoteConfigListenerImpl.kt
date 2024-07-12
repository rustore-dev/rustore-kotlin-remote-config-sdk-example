package ru.rustore.remoteconfigexample

import android.util.Log
import ru.rustore.sdk.remoteconfig.RemoteConfigClientEventListener
import ru.rustore.sdk.remoteconfig.RemoteConfigException

class RemoteConfigListenerImpl: RemoteConfigClientEventListener {
    override fun backgroundJobErrors(exception: RemoteConfigException.BackgroundConfigUpdateError) {
        //Возвращает ошибку фоновой работы
    }

    override fun firstLoadComplete() {
        Log.d("HOHOHO", "firstLoadComplete")
    }

    override fun initComplete() {
        Log.d("HOHOHO", "initComplete")
    }

    override fun memoryCacheUpdated() {
        Log.d("HOHOHO", "memoryCacheUpdated")
    }

    override fun persistentStorageUpdated() {
        Log.d("HOHOHO", "persistentStorageUpdated")
    }

    override fun remoteConfigNetworkRequestFailure(throwable: Throwable) {
        Log.d("HOHOHO", "remoteConfigNetworkRequestFailure")
    }
}
