package com.example.movies.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.R
import com.example.movies.databinding.ActivityMoviesBinding
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.base.BaseActivity
import com.example.movies.presentation.base.OnRecyclerViewItemClickListener
import com.example.movies.presentation.movieDetails.DETAILS_INTENT_KEY
import com.example.movies.presentation.movieDetails.MovieDetailsActivity
import com.example.movies.presentation.searchMovies.SearchMoviesActivity
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
        adapter.onRecyclerViewItemClickListener = object : OnRecyclerViewItemClickListener<Movie> {
            override fun onItemClicked(item: Movie) {
                val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
                intent.putExtra(DETAILS_INTENT_KEY, item)
                startActivity(intent)
            }
        }
        val layoutManager = LinearLayoutManager(this)
        binder.moviesRecyclerView.layoutManager = layoutManager
        binder.moviesRecyclerView.adapter = adapter

    }

    override fun observeViewModels() {
        moviesViewModel.moviesListLiveData.observe(this, ::updateMoviesList)
        moviesViewModel.showProgressBarLiveData.observe(this, ::observeShowProgressDialog)
        moviesViewModel.hideProgressBarLiveData.observe(this, ::observeHideProgressDialog)
        moviesViewModel.toastLiveData.observe(this, ::handleToast)
    }

    private fun updateMoviesList(list: List<Movie>) {
        Log.d("VIEWMODELMOVIES", "ACTIVITY ${list.size}")

        adapter.submitList(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search)
            startActivity(Intent(this, SearchMoviesActivity::class.java))
        return super.onOptionsItemSelected(item)
    }
}
