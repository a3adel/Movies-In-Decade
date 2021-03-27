package com.example.movies.presentation.searchMovies

import android.content.Context
import com.example.movies.R
import com.example.movies.presentation.models.UiValidationResult
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchMoviesInputValidator @Inject constructor(@ApplicationContext private val context: Context) {
    fun validateQuery(query: String): UiValidationResult {
        if (query.isNotEmpty()) {
            return UiValidationResult(true)
        } else {
            return UiValidationResult(false, context.getString(R.string.invalid_query))
        }
    }
}