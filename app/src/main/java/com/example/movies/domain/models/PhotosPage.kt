package com.example.movies.domain.models

data class PhotosPage(
    val page: Int,
    val photos: List<Photo>
)
