package com.example.movies.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotosPageEntity(
    @Json(name = "page") val page: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "perpage") val photosPerPage: Int,
    @Json(name = "total") val totalPhotos: Int,
    @Json(name = "photo") val photosData: List<PhotoEntity>

)
