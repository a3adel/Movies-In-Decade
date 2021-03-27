package com.example.movies.data.repos.images

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.example.movies.data.entities.ImageSearchEntity

class ImagesDataSource:PageKeyedDataSource<Int, ImageSearchEntity>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ImageSearchEntity>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ImageSearchEntity>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ImageSearchEntity>
    ) {
        TODO("Not yet implemented")
    }

}