package com.example.movies.domain.models

data class Movie(
    val title: String,
    val year: Int,
    val cast: List<String>? = null,
    val genres: List<String>? = null,
    val rating: Float
)