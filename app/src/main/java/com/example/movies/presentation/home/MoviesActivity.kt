package com.example.movies.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.databinding.ActivityMoviesBinding
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : BaseActivity() {
    private lateinit var binder: ActivityMoviesBinding
    private val moviesViewModel: MoviesViewModel by viewModels()
    private val adapter = MoviesAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binder.root)
        super.onCreate(savedInstanceState)
        moviesViewModel.getMovies()
    }

    override fun initViews() {
        val layoutManager = LinearLayoutManager(this)
        binder.moviesRecyclerView.layoutManager = layoutManager
        binder.moviesRecyclerView.adapter = adapter
    }

    override fun observeViewModels() {
        moviesViewModel.moviesListLiveData.observe(this, ::updateMoviesList)
        moviesViewModel.showProgressBarLiveData.observe(this,::observeShowProgressDialog)
        moviesViewModel.hideProgressBarLiveData.observe(this,::observeHideProgressDialog)
        moviesViewModel.toastLiveData.observe(this,::handleToast)
    }

    private fun updateMoviesList(list: List<Movie>) {
        Log . d ("VIEWMODELMOVIES","ACTIVITY ${list.size}")

        adapter.submitList(list)
    }


}
