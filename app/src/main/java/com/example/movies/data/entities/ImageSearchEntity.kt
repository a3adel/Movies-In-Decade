package com.example.movies.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageSearchEntity(@Json(name = "photos")val photosPage:PhotosPageEntity)
