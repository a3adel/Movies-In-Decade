package com.example.movies.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesEntity(@Json(name = "movies") val movies: List<MovieEntity>)