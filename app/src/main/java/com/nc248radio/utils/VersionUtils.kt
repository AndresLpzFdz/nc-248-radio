package com.nc248radio.utils

import com.nc248radio.BuildConfig

object VersionUtils {

    @JvmStatic
    fun getVersionString(): String {
        val versionCode: Int = BuildConfig.VERSION_CODE
        val versionName: String = BuildConfig.VERSION_NAME

        return "$versionName ($versionCode)"
    }

}