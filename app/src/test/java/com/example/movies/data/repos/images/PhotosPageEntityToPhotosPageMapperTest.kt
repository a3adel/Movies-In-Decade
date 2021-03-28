package com.example.movies.data.repos.images

import com.example.movies.PHOTOS_ENTITY_PAGE
import com.example.movies.PHOTOS_PAGE
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class PhotosPageEntityToPhotosPageMapperTest {
    lateinit var photoEntityToPhotoMapper: PhotoEntityToPhotoMapper
    lateinit var SUT: PhotosPageEntityToPhotosPageMapper

    @Before
    fun setup() {
        photoEntityToPhotoMapper = PhotoEntityToPhotoMapper()
        SUT = PhotosPageEntityToPhotosPageMapper(photoEntityToPhotoMapper)
    }

    @Test
    fun `mapfrom should return photosPage Object`(){
        Assert.assertEquals(PHOTOS_PAGE,SUT.mapFrom(PHOTOS_ENTITY_PAGE))
    }
}