package com.example.movies.data.repos.movies

import android.content.Context
import com.example.movies.R
import com.example.movies.data.local.moviesFileManager.MOVIES_ERROR_Causes
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
            Pair(MOVIES_ERROR_Causes.FILE_NOT_FOUND, context.getString(R.string.no_file)),
            Pair(MOVIES_ERROR_Causes.PARSE_EXCEPTION, context.getString(R.string.parsing_error)),
            Pair(MOVIES_ERROR_Causes.NULL_EXCEPTION, context.getString(R.string.default_error)),
            Pair(MOVIES_ERROR_Causes.EMPTY_MOVIES_LIST, context.getString(R.string.no_movies_found)),

            )
}