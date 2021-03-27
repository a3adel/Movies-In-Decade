package com.example.movies.di

import com.example.movies.data.local.fileReader.FileReader
import com.example.movies.data.local.fileReader.FileReaderImpl
import com.example.movies.data.memory.MemoryCacheInterface
import com.example.movies.data.memory.MemoryCacheInterfaceImpl
import com.example.movies.data.remote.ApiConstants
import com.example.movies.presentation.base.Dispatcher
import com.example.movies.presentation.base.DispatcherImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideDispatcher(dispatcherImpl: DispatcherImpl): Dispatcher = dispatcherImpl

    @Singleton
    @Provides
    fun provideMemoryCacheInterface(memoryCacheInterfaceImpl: MemoryCacheInterfaceImpl): MemoryCacheInterface =
        memoryCacheInterfaceImpl
}