package com.nc248radio.app

import android.app.Application
import android.content.Context
import com.nc248radio.data.local.PreferenceHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext //Context

        PreferenceHelper.init(this) //Shared Preferences
    }
}