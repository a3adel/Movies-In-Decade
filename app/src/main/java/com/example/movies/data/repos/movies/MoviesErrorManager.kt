package com.example.movies.data.repos.movies

import android.content.Context
import com.example.movies.R
import com.example.movies.data.local.moviesFileManager.Causes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MoviesErrorManager @Inject constructor(@ApplicationContext private val context: Context) {
    fun getErrorMessage(key: String): String {
        if (errorsMap.containsKey(key))
            if(errorsMap[key] !=null)
                return errorsMap[key]!!
        return context.getString(R.string.default_error)
    }

    private val errorsMap: Map<String, String>
        get() = mapOf(
            Pair(Causes.FILE_NOT_FOUND, context.getString(R.string.no_file)),
            Pair(Causes.PARSE_EXCEPTION, context.getString(R.string.parsing_error)),
            Pair(Causes.NULL_EXCEPTION, context.getString(R.string.default_error)),

            )
}