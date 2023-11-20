package ru.rustore.remoteconfigexample

import android.app.Application
import ru.rustore.sdk.remoteconfig.AppId
import ru.rustore.sdk.remoteconfig.AppVersion
import ru.rustore.sdk.remoteconfig.RemoteConfigClientBuilder
import ru.rustore.sdk.remoteconfig.UpdateBehaviour

class Application : Application() {
    override fun onCreate() {

        super.onCreate()

        RemoteConfigClientBuilder(
            appId = AppId("dbc86247-407c-47c7-8c82-0aca35232b24"),
            context = applicationContext
        ).setAppVersion(AppVersion(BuildConfig.VERSION_NAME))
            .setUpdateBehaviour(UpdateBehaviour.Actual)
            .build()
            .init()

    }
}