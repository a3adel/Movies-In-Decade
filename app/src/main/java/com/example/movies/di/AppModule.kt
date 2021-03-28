package com.example.movies.di

import com.example.movies.data.local.fileReader.FileReader
import com.example.movies.data.local.fileReader.FileReaderImpl
import com.example.movies.data.memory.MemoryCacheInterface
import com.example.movies.data.memory.MemoryCacheInterfaceImpl
import com.example.movies.data.remote.ApiConstants
import com.example.movies.data.utils.NetworkConnectivity
import com.example.movies.data.utils.NetworkConnectivityImpl
import com.example.movies.presentation.base.Dispatcher
import com.example.movies.presentation.base.DispatcherImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
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

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .protocols(listOf(Protocol.HTTP_1_1))
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideDispatcher(dispatcherImpl: DispatcherImpl): Dispatcher = dispatcherImpl

    @Singleton
    @Provides
    fun provideMemoryCacheInterface(memoryCacheInterfaceImpl: MemoryCacheInterfaceImpl): MemoryCacheInterface =
        memoryCacheInterfaceImpl

    @Singleton
    @Provides
    fun provideNetworkConnectivity(networkConnectivityImpl: NetworkConnectivityImpl):NetworkConnectivity = networkConnectivityImpl
}