package com.example.movies.data.repos.images

import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.PhotosRepo
import com.example.movies.domain.models.Photo

class PhotosRepoImpl: PhotosRepo {
    override fun searchPhotos(query: String): Resource<List<Photo>> {
        TODO("Not yet implemented")
    }
}