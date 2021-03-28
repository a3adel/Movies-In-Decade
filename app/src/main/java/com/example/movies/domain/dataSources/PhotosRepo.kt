package com.example.movies.domain.dataSources

import androidx.paging.PagingData
import com.example.movies.data.Resource
import com.example.movies.domain.models.Photo
import com.example.movies.domain.models.PhotosPage
import kotlinx.coroutines.flow.Flow

interface PhotosRepo {
     fun searchPhotos(query:String): Flow<PagingData<Photo>>
}