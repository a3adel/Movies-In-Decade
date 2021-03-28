package com.example.movies.presentation.movieDetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.databinding.ActivityMovieDetailsBinding
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val DETAILS_INTENT_KEY = "details_intent"

@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {
    private lateinit var binder: ActivityMovieDetailsBinding
    private val photosViewModel: PhotosViewModel by viewModels()
    private val photosAdapter = PhotosAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        binder = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binder.root)
        super.onCreate(savedInstanceState)
        val movie = intent.getParcelableExtra<Movie>(DETAILS_INTENT_KEY)
        movie?.let {
            binder.nameTextView.text = movie.title
            binder.yearTextView.text = movie.year.toString()
            binder.rateValueTextView.text = movie.rating.toString()
            movie.cast?.let { cast -> binder.castTextView.text = cast.toString() }
            movie.genres?.let { genres -> binder.genresTextView.text = genres.toString() }
            search(movie.title)
        }

    }

    private var searchJob: Job? = null

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            photosViewModel.searchPhotos(query).collectLatest {
                photosAdapter.submitData(it)
            }
        }
    }

    override fun initViews() {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binder.imagesRecyclerView.layoutManager = gridLayoutManager
        binder.imagesRecyclerView.adapter = photosAdapter
    }

    override fun observeViewModels() {
    }
}