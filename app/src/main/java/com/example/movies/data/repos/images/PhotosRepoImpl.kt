package com.example.movies.data.repos.images

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movies.data.remote.PhotosApiInterface.Companion.NETWORK_PAGE_SIZE
import com.example.movies.domain.dataSources.PhotosRepo
import com.example.movies.domain.models.Photo
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class PhotosRepoImpl @Inject constructor(private val pagingSource: PhotosPagingSource) :
    PhotosRepo {
    override fun searchPhotos(query: String): Flow<PagingData<Photo>> {
        pagingSource.setQuery(query)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

}