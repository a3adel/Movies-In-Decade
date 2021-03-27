package com.example.movies.data.remote

import com.example.movies.data.entities.PhotosPageEntity
import retrofit2.Response

interface PhotosApiInterface {
    suspend fun searchPhotos():Response<PhotosPageEntity>
}