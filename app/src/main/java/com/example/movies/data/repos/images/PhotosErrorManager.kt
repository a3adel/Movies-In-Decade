package com.example.movies.data.repos.images

import android.content.Context
import com.example.movies.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PhotosErrorManager @Inject constructor(@ApplicationContext private val context: Context) {
    fun getErrorMessage(key: String): String {
        if (errorsMap.containsKey(key))
            if (errorsMap[key] != null)
                return errorsMap[key]!!
        return context.getString(R.string.default_error)
    }

    private val errorsMap: Map<String, String>
        get() = mapOf(
            Pair(PHOTOS_ERRORS_CAUSES.NETWORK_ERROR, context.getString(R.string.no_network)),
            Pair(PHOTOS_ERRORS_CAUSES.REQUEST_ERROR, context.getString(R.string.default_error))
        )
}