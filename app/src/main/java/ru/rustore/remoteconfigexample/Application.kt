package ru.rustore.remoteconfigexample

import android.app.Application
import ru.rustore.sdk.remoteconfig.AppId
import ru.rustore.sdk.remoteconfig.AppVersion
import ru.rustore.sdk.remoteconfig.DeviceId
import ru.rustore.sdk.remoteconfig.RemoteConfigClientBuilder
import ru.rustore.sdk.remoteconfig.RemoteConfigClientEventListener
import ru.rustore.sdk.remoteconfig.UpdateBehaviour

class Application : Application() {
    override fun onCreate() {

        super.onCreate()

        val listener = RemoteConfigListenerImpl()

        RemoteConfigClientBuilder(
            appId = AppId("dbc86247-407c-47c7-8c82-0aca35232b24"),
            context = applicationContext
        ).setDeviceId(DeviceId("111"))
            .setAppVersion(AppVersion(BuildConfig.VERSION_NAME))
            .setUpdateBehaviour(UpdateBehaviour.Actual)
            .setRemoteConfigClientEventListener(listener)
            .build()
            .init()
    }
}