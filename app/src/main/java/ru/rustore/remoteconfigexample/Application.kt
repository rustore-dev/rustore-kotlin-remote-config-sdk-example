package ru.rustore.remoteconfigexample

import android.app.Application
import ru.rustore.sdk.remoteconfig.Account
import ru.rustore.sdk.remoteconfig.AppId
import ru.rustore.sdk.remoteconfig.AppVersion
import ru.rustore.sdk.remoteconfig.ConfigRequestParameter
import ru.rustore.sdk.remoteconfig.ConfigRequestParameterProvider
import ru.rustore.sdk.remoteconfig.DeviceId
import ru.rustore.sdk.remoteconfig.Language
import ru.rustore.sdk.remoteconfig.RemoteConfigClientBuilder
import ru.rustore.sdk.remoteconfig.UpdateBehaviour
import kotlin.time.Duration.Companion.minutes

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        val context = applicationContext

        val behaviour = when (Settings.updateBehaviour(context)) {
            UpdateBehaviourType.ACTUAL -> UpdateBehaviour.Actual
            UpdateBehaviourType.DEFAULT -> UpdateBehaviour.Default(Settings.updateTime(context).minutes)
            UpdateBehaviourType.SNAPSHOT -> UpdateBehaviour.Snapshot(Settings.updateTime(context).minutes)
        }

        val paramsProvider = object : ConfigRequestParameterProvider {
            override fun getConfigRequestParameter(): ConfigRequestParameter =
                ConfigRequestParameter(
                    language = Settings.language(context)
                        .takeIf { it.isNotBlank() }
                        ?.let { Language(it) },
                    account = Settings.account(context)
                        .takeIf { it.isNotBlank() }
                        ?.let { Account(it) },
                )
        }

        RemoteConfigClientBuilder(
            appId = AppId(Settings.appId(context)),
            context = context,
        )
            .setDeviceId(DeviceId(Settings.deviceId(context)))
            .setAppVersion(AppVersion(BuildConfig.VERSION_NAME))
            .setUpdateBehaviour(behaviour)
            .setConfigRequestParameterProvider(paramsProvider)
            .setRemoteConfigClientEventListener(RemoteConfigListenerImpl())
            .build()
            .init()
    }
}
