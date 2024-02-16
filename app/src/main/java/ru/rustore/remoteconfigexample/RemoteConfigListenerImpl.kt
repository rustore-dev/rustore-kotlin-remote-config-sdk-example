package ru.rustore.remoteconfigexample

import android.util.Log
import ru.rustore.sdk.remoteconfig.RemoteConfigClientEventListener
import ru.rustore.sdk.remoteconfig.RemoteConfigException

class RemoteConfigListenerImpl: RemoteConfigClientEventListener {
    override fun backgroundJobErrors(exception: RemoteConfigException.BackgroundConfigUpdateError) {
        //Возвращает ошибку фоновой работы
    }

    override fun firstLoadComplete() {
        Log.d("HOHOHO", "First Load ended")
    }

    override fun initComplete() {
        TODO("Not yet implemented")
    }

    override fun memoryCacheUpdated() {
        TODO("Not yet implemented")
    }

    override fun persistentStorageUpdated() {
        TODO("Not yet implemented")
    }

    override fun remoteConfigNetworkRequestFailure(throwable: Throwable) {
        TODO("Not yet implemented")
    }
}