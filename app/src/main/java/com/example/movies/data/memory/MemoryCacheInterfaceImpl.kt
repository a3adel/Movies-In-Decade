package com.example.movies.data.memory

import com.example.movies.domain.models.Movie
import javax.inject.Inject

class MemoryCacheInterfaceImpl @Inject constructor() : MemoryCacheInterface {
    private var moviesList: List<Movie>? = null
    override fun setMovies(movies: List<Movie>) {
        moviesList = movies
    }

    override fun getMovies(): List<Movie>? {
        return moviesList
    }

    override fun destroyMovies() {
        moviesList = null
    }
}