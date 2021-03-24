package com.example.movies.di

import com.example.movies.data.local.fileReader.FileReader
import com.example.movies.data.local.fileReader.FileReaderImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideMoshi() = Moshi.Builder().build()

    @Singleton
    @Provides
    fun provideFileReader(fileReaderImpl: FileReaderImpl): FileReader = fileReaderImpl
}