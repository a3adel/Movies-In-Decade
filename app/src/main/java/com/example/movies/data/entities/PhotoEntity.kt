package com.example.movies.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoEntity(
    @Json(name = "id") val id: String,
    @Json(name = "owner") val owner: String,
    @Json(name = "secret") val secret: String,
    @Json(name = "server") val server: String,
    @Json(name = "farm") val farm: String,
    @Json(name = "title") val title: String
)
