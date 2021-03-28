package com.example.movies.data.repos.images

import com.example.movies.data.base.Mapper
import com.example.movies.data.entities.PhotoEntity
import com.example.movies.domain.models.Photo
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PhotoEntityToPhotoMapper @Inject constructor() :
    Mapper<PhotoEntity, Photo>() {
    override fun mapFrom(from: PhotoEntity): Photo {
        val url =
            "https://farm${from.farm}.static.flickr.com/${from.server}/${from.id}_${from.secret}.jpg"
        return Photo(title = from.title, link = url)
    }
}