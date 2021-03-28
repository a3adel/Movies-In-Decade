package com.example.movies.data.repos.images

import com.example.movies.PHOTO_1
import com.example.movies.PHOTO_ENTITY_1
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PhotoEntityToPhotoMapperTest {
    lateinit var SUT: PhotoEntityToPhotoMapper
    @Before
    fun setup(){
        SUT = PhotoEntityToPhotoMapper()
    }

    @Test
    fun `mapfrom photoEntity should return Photo object with title and valid url`(){
        Assert.assertEquals(PHOTO_1,SUT.mapFrom(PHOTO_ENTITY_1))
    }
}
