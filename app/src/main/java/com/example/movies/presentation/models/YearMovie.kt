package com.example.movies.presentation.models

data class YearMovie(
    val title: String,
    val cast: List<String>? = null,
    val genres: List<String>? = null,
    val rating: Float
)