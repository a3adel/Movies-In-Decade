package com.example.movies.presentation.movieDetails

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.domain.models.Photo
import com.example.movies.domain.useCases.SearchPhotosUseCase
import com.example.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val searchPhotosUseCase: SearchPhotosUseCase) : BaseViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Photo>>? = null

    fun searchPhotos(query: String): Flow<PagingData<Photo>> {
        val lastResult = currentSearchResult
        lastResult?.let {
            if (query == currentQueryValue)
                return lastResult
        }
        currentQueryValue = query
        val newResult = searchPhotosUseCase.searchPhotos(query).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}