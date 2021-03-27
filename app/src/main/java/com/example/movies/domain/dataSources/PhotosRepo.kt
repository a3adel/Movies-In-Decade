package com.example.movies.domain.dataSources

import com.example.movies.data.Resource
import com.example.movies.domain.models.Photo

interface PhotosRepo {
    fun searchPhotos(query:String): Resource<List<Photo>>
}