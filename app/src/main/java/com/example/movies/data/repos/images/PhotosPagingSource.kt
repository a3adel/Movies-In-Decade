package com.example.movies.data.repos.images

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.data.Resource
import com.example.movies.data.remote.PhotosApiInterface.Companion.NETWORK_PAGE_SIZE
import com.example.movies.domain.models.Photo
import javax.inject.Inject

private const val PHOTOS_STARTING_PAGE_INDEX = 1

class PhotosPagingSource @Inject constructor(
    private val remoteClient: PhotosRemoteClient
) : PagingSource<Int, Photo>() {
    private var query: String? = null
    fun setQuery(query: String) {
        this.query = query
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {

        val position = params.key ?: PHOTOS_STARTING_PAGE_INDEX
        return query?.let {query->
            val resource = remoteClient.searchPhotos(query, position, params.loadSize)
             when (resource) {
                is Resource.Success -> {
                    val nextKey = if (resource.data.photos.isEmpty()) {
                        null
                    } else {
                        // initial load size = 3 * NETWORK_PAGE_SIZE
                        // ensure we're not requesting duplicating items, at the 2nd request
                        position + (params.loadSize / NETWORK_PAGE_SIZE)
                    }
                    LoadResult.Page(
                        data = resource.data.photos,
                        prevKey = if (position == PHOTOS_STARTING_PAGE_INDEX) null else position - 1,
                        nextKey = nextKey
                    )
                }
                is Resource.Error -> {
                    LoadResult.Error(Throwable(resource.message))
                }
            }
        } ?: kotlin.run { LoadResult.Error(Throwable("query can't be null")) }
    }
}