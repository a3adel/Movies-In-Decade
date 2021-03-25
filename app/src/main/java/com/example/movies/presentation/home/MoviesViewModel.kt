package com.example.movies.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Resource
import com.example.movies.domain.models.Movie
import com.example.movies.domain.useCases.TopRatedMoviesUseCase
import com.example.movies.presentation.base.BaseViewModel
import com.example.movies.presentation.base.Dispatcher
import com.example.movies.presentation.base.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase,
    private val dispatcher: Dispatcher
) :
    BaseViewModel() {
    private val moviesListMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesListLiveData: LiveData<List<Movie>> get() = moviesListMutableLiveData

    fun getMovies() {
        viewModelScope.launch(dispatcher.io()) {
            showProgressBarMutableLiveData.postValue(SingleEvent(Unit))
            when (val moviesResource = topRatedMoviesUseCase.getTopRatedMovies()) {
                is Resource.Success -> {
                    hideProgressBarMutableLiveData.postValue(SingleEvent(Unit))

                    moviesResource.data?.let { data -> moviesListMutableLiveData.postValue(data) }
                }
                is Resource.Error -> {
                    hideProgressBarMutableLiveData.postValue(SingleEvent(Unit))

                    moviesResource.message?.let { message ->
                        toastMutableLiveData.postValue(
                            SingleEvent(message)
                        )
                    }
                }
            }
        }
    }
}