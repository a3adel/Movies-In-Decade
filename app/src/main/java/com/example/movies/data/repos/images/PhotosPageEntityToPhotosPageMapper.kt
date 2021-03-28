package com.example.movies.data.repos.images

import com.example.movies.data.base.Mapper
import com.example.movies.data.entities.PhotosPageEntity
import com.example.movies.domain.models.PhotosPage
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class PhotosPageEntityToPhotosPageMapper @Inject constructor(private val photoEntityToPhotosMapper: PhotoEntityToPhotoMapper) :
    Mapper<PhotosPageEntity, PhotosPage>() {
    override fun mapFrom(from: PhotosPageEntity): PhotosPage {
        return PhotosPage(
            from.page,
            from.pages,
            photos = from.photosData.map {photoEntity ->  photoEntityToPhotosMapper.mapFrom(photoEntity) })
    }
}