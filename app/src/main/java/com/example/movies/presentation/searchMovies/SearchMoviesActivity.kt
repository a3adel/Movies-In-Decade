package com.example.movies.presentation.searchMovies

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivitySearchMoviesBinding
import com.example.movies.presentation.base.BaseActivity
import com.example.movies.presentation.base.SingleEvent
import com.example.movies.presentation.base.textChanges
import com.example.movies.presentation.models.YearMovies
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesActivity : BaseActivity() {
    private lateinit var binder: ActivitySearchMoviesBinding
    private val searchViewModel: SearchMoviesViewModel by viewModels()
    val searchResultsAdapter = SearchResultsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivitySearchMoviesBinding.inflate(layoutInflater)
        setContentView(binder.root)
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this)
        binder.searchResultsRecyclerView.layoutManager = linearLayoutManager
        binder.searchResultsRecyclerView.adapter = searchResultsAdapter
        searchViewModel.viewModelScope.launch {
            binder.queryEditText.textChanges().debounce(750).collect {
                searchViewModel.searchMovies(it.toString())
            }
        }

    }

    override fun observeViewModels() {
        searchViewModel.showProgressBarLiveData.observe(this, ::observeShowProgressDialog)
        searchViewModel.hideProgressBarLiveData.observe(this, ::observeHideProgressDialog)
        searchViewModel.toastLiveData.observe(this, ::handleToast)
        searchViewModel.yearsMoviesLiveData.observe(this, ::handleSearchResult)
        searchViewModel.invalidQueryLiveData.observe(this, ::handleInvalidQuery)
    }

    private fun handleInvalidQuery(singleEvent: SingleEvent<String>?) {
        searchResultsAdapter.clearList()
    }

    private fun handleSearchResult(list: List<YearMovies>) {
        searchResultsAdapter.submitList(list)
    }
}