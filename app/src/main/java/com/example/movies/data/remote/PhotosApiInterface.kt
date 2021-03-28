package com.example.movies.data.remote

import com.example.movies.BuildConfig
import com.example.movies.data.entities.ImageSearchEntity
import com.example.movies.data.entities.PhotosPageEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiInterface {
    @GET("services/rest")
    suspend fun searchPhotos(
        @Query(ApiConstants.API_QUERY_KEY) apiKey: String = BuildConfig.API_KEY,
        @Query(ApiConstants.FORMAT_QUERY_KEY) format: String = ApiConstants.FORMAT_QUERY_VALUE,
        @Query(ApiConstants.METHOD_QUERY_KEY) method: String = ApiConstants.METHOD_QUERY_VALUE,
        @Query(ApiConstants.NO_JSON_CALL_BACK_QUERY_KEY) noJsonCallBack: String = ApiConstants.NO_JSON_CALL_BACK_QUERY_VALUE,
        @Query(ApiConstants.PER_PAGE_QUERY_KEY) perPage: Int = ApiConstants.PER_PAGE_QUERY_VALUE,
        @Query(ApiConstants.PAGE_QUERY_KEY) page: String,
        @Query(ApiConstants.TEXT_QUERY_KEY) text: String
    ): Response<ImageSearchEntity>
    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }
}