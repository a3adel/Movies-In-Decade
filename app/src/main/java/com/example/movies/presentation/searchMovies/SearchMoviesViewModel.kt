package com.example.movies.presentation.searchMovies

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movies.data.Resource
import com.example.movies.domain.useCases.SearchMoviesUseCase
import com.example.movies.presentation.base.BaseViewModel
import com.example.movies.presentation.base.Dispatcher
import com.example.movies.presentation.base.SingleEvent
import com.example.movies.presentation.models.YearMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val searchValidator: SearchMoviesInputValidator,
    private val dispatcher: Dispatcher
) : BaseViewModel() {
    private val invalidQueryMutableLiveData = MutableLiveData<SingleEvent<String>>()
    val invalidQueryLiveData: LiveData<SingleEvent<String>> get() = invalidQueryMutableLiveData

    private val yearsMoviesMutableLiveData = MutableLiveData<List<YearMovies>>()
    val yearsMoviesLiveData: LiveData<List<YearMovies>> get() = yearsMoviesMutableLiveData

    private val noSearchResultsMutableLiveData = MutableLiveData<Unit>()
    val noSearchResultsLiveData: LiveData<Unit> get() = noSearchResultsMutableLiveData

    @SuppressLint("NullSafeMutableLiveData")
    fun searchMovies(query: String) {
        val validQueryUiResult = searchValidator.validateQuery(query)
        if (validQueryUiResult.isValid) {
            showProgressBarMutableLiveData.value=(SingleEvent(Unit))
            viewModelScope.launch(dispatcher.io()) {
                when (val searchResource = searchMoviesUseCase.searchMovies(query)) {
                    is Resource.Success -> {
                        hideProgressBarMutableLiveData.postValue(SingleEvent(Unit))

                        if (searchResource.data.isNotEmpty()){
                            yearsMoviesMutableLiveData.postValue(searchResource.data)
                        }
                        else{
                            noSearchResultsMutableLiveData.postValue(Unit)


                        }
                    }
                    is Resource.Error -> {
                        hideProgressBarMutableLiveData.postValue(SingleEvent(Unit))

                        toastMutableLiveData.postValue(SingleEvent(searchResource.message))
                    }

                }
            }
        } else
            validQueryUiResult.message?.let { message ->
                invalidQueryMutableLiveData.value = SingleEvent(message)
            } ?: kotlin.run {
                //In case the validator returned null from the strings file, I return hard coded String error message
                invalidQueryMutableLiveData.value =
                    SingleEvent("something went wrong, please try again later")
            }
    }
}