package com.example.movies.data.repos.images

import com.example.movies.data.remote.PhotosApiInterface
import com.example.movies.data.utils.NetworkConnectivity
import javax.inject.Inject

class PhotosRemoteClient @Inject constructor(
    private val photosApiInterface: PhotosApiInterface,
    private val networkConnectivity: NetworkConnectivity,
    private val photosPageEntityToPhotosPageMapper: PhotosPageEntityToPhotosPageMapper
) {

}