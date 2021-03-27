package com.example.movies

import com.example.movies.data.entities.PhotoEntity
import com.example.movies.data.entities.PhotosPageEntity
import com.example.movies.domain.models.Photo
import com.example.movies.domain.models.PhotosPage

val PHOTO_ENTITY_1 = PhotoEntity(
    id = "51071989798", owner = "adel", secret = "b9fd95c279", server = "65535", farm = "66", title =
    "cool"
)

val PHOTO_ENTITY_2 = PhotoEntity(
    id = "51072789612", owner = "adel2", secret = "e7cacf66fb", server = "65535", farm = "66", title =
    "cool2"
)
val PHOTOS_ENTITY_LIST= listOf(PHOTO_ENTITY_1, PHOTO_ENTITY_2)
val PHOTOS_ENTITY_PAGE=PhotosPageEntity(1,10,20,30, PHOTOS_ENTITY_LIST)

val PHOTO_1 = Photo(title = "cool",link = "http://farm66.static.flickr.com/65535/51071989798_b9fd95c279.jpg")
val PHOTO_2 = Photo(title = "cool2",link = "http://farm66.static.flickr.com/65535/51072789612_e7cacf66fb.jpg")
val PHOTOS_LIST= listOf(PHOTO_1, PHOTO_2)
val PHOTOS_PAGE=PhotosPage(1, PHOTOS_LIST)