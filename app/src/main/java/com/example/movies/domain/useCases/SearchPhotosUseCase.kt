package com.example.movies.domain.useCases

import androidx.paging.PagingData
import com.example.movies.domain.dataSources.PhotosRepo
import com.example.movies.domain.models.Photo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SearchPhotosUseCase @Inject constructor(private val repo: PhotosRepo) {
     fun searchPhotos(query: String): Flow<PagingData<Photo>> =
        repo.searchPhotos(query)

}