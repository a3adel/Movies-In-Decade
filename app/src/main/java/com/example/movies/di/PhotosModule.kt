package com.example.movies.di

import com.example.movies.data.remote.PhotosApiInterface
import com.example.movies.data.repos.images.PhotosRepoImpl
import com.example.movies.domain.dataSources.PhotosRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class PhotosModule {
    @Provides
    @ViewModelScoped
    fun providePhotosEndPoint(retrofit: Retrofit) = retrofit.create(PhotosApiInterface::class.java)

    @Provides
    @ViewModelScoped
    fun providePhotosRepo(photosRepoImpl: PhotosRepoImpl): PhotosRepo = photosRepoImpl
}