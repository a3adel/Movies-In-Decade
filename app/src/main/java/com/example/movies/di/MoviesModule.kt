package com.example.movies.di

import com.example.movies.data.local.moviesFileManager.MoviesFileInterface
import com.example.movies.data.local.moviesFileManager.MoviesFileInterfaceImpl
import com.example.movies.data.remote.PhotosApiInterface
import com.example.movies.data.repos.movies.MoviesRepoImpl
import com.example.movies.domain.dataSources.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MoviesModule {
    @Provides
    @ViewModelScoped
    fun provideMoviesRepo(moviesRepoImpl: MoviesRepoImpl): MoviesRepo = moviesRepoImpl

    @Provides
    @ViewModelScoped
    fun provideMovieFileInterface(moviesFileInterfaceImpl: MoviesFileInterfaceImpl): MoviesFileInterface =
        moviesFileInterfaceImpl

}