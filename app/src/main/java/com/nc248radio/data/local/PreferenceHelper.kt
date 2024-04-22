package com.nc248radio.data.local

import android.content.Context
import android.content.SharedPreferences
import com.nc248radio.BuildConfig

object PreferenceHelper {

    private const val PREFERENCES_NAME = "${BuildConfig.APPLICATION_ID}_preferences"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    //List of preferences
    val AUTH_TOKEN_KEY = Pair("auth_token", "") // Key, Default Value

    var authToken: String
        get() = preferences[AUTH_TOKEN_KEY.first, AUTH_TOKEN_KEY.second]
        set(value) = preferences.set(AUTH_TOKEN_KEY.first, value)

    //Methods

    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?)
            = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T
            = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

}