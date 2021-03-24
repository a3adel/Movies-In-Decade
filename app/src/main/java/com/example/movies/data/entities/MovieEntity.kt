package com.example.movies.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieEntity(
    @Json(name = "title") val title: String,
    @Json(name = "year") val year: Int,
    @Json(name = "cast") val cast: List<String>? = null,
    @Json(name = "genres") val genres: List<String>? = null,
    @Json(name = "rating") val rating: Float
)
