package com.example.movies.data.repos.images

import com.example.movies.data.Resource
import com.example.movies.data.remote.PhotosApiInterface
import com.example.movies.data.repos.images.PHOTOS_ERRORS_CAUSES.NETWORK_ERROR
import com.example.movies.data.repos.images.PHOTOS_ERRORS_CAUSES.REQUEST_ERROR
import com.example.movies.data.utils.NetworkConnectivity
import com.example.movies.domain.models.PhotosPage
import javax.inject.Inject

class PhotosRemoteClient @Inject constructor(
    private val photosApiInterface: PhotosApiInterface,
    private val networkConnectivity: NetworkConnectivity,
    private val photosPageEntityToPhotosPageMapper: PhotosPageEntityToPhotosPageMapper,
    private val errorManager: PhotosErrorManager
) {
    suspend fun searchPhotos(query: String, page: Int,itemsPerPage:Int): Resource<PhotosPage> {
        return if (networkConnectivity.isConnected()) {
            val response = photosApiInterface.searchPhotos(text = query, page = page.toString(),perPage = itemsPerPage)
            if (response.isSuccessful)
                response.body()?.let { responseBody ->
                    Resource.Success(photosPageEntityToPhotosPageMapper.mapFrom(responseBody.photosPage))
                } ?: run {
                    Resource.Error(
                        errorManager.getErrorMessage(REQUEST_ERROR),
                        REQUEST_ERROR
                    )
                }
            else {
                Resource.Error(
                    errorManager.getErrorMessage(REQUEST_ERROR),
                    REQUEST_ERROR
                )
            }
        } else
            Resource.Error(
                errorManager.getErrorMessage(PHOTOS_ERRORS_CAUSES.NETWORK_ERROR),
                NETWORK_ERROR
            )
    }
}