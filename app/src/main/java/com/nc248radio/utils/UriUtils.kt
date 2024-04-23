package com.nc248radio.utils

import android.net.Uri
import com.nc248radio.BuildConfig

object UriUtils {

    fun getURIForResource(resourceId: Int): Uri {
        return Uri.parse(
            "android.resource://" + BuildConfig.APPLICATION_ID + "/" + resourceId
        )
    }

}