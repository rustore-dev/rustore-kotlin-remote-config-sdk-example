package ru.rustore.remoteconfigexample

import android.content.Context
import android.content.SharedPreferences

object Settings {

    private const val PREFERENCES_NAME = "rustore_shared_preferences"

    private const val KEY_APP_ID = "rustore_app_id"
    private const val KEY_DEVICE_ID = "rustore_device_id"
    private const val KEY_ACCOUNT = "rustore_account"
    private const val KEY_LANGUAGE = "rustore_language"
    private const val KEY_UPDATE_BEHAVIOUR = "rustore_update_behaviour"
    private const val KEY_UPDATE_TIME = "rustore_update_time"

    fun preferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun appId(context: Context): String =
        preferences(context).getString(KEY_APP_ID, null)
            ?: context.getString(R.string.rustore_app_id)

    fun deviceId(context: Context): String =
        preferences(context).getString(KEY_DEVICE_ID, null)
            ?: context.getString(R.string.rustore_device_id)

    fun account(context: Context): String =
        preferences(context).getString(KEY_ACCOUNT, null)
            ?: context.getString(R.string.rustore_account)

    fun language(context: Context): String =
        preferences(context).getString(KEY_LANGUAGE, null)
            ?: context.getString(R.string.rustore_language)

    fun updateBehaviour(context: Context): UpdateBehaviourType =
        UpdateBehaviourType.parse(preferences(context).getString(KEY_UPDATE_BEHAVIOUR, null))
            ?: UpdateBehaviourType.parse(context.getString(R.string.rustore_update_behaviour))
            ?: UpdateBehaviourType.ACTUAL

    fun updateTime(context: Context): Int =
        preferences(context).getInt(KEY_UPDATE_TIME, -1)
            .takeIf { it > 0 }
            ?: context.resources.getInteger(R.integer.rustore_update_time)

    fun saveAccount(context: Context, value: String) {
        preferences(context).edit().putString(KEY_ACCOUNT, value).apply()
    }

    fun saveLanguage(context: Context, value: String) {
        preferences(context).edit().putString(KEY_LANGUAGE, value).apply()
    }

    fun saveUpdateBehaviour(context: Context, type: UpdateBehaviourType) {
        // Synchronous write: the process is restarted right after (ProcessPhoenix);
        // apply() may not flush to disk before the process exits.
        preferences(context).edit().putString(KEY_UPDATE_BEHAVIOUR, type.name).commit()
    }
}
