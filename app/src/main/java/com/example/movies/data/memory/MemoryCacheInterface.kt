package com.example.movies.data.memory

import com.example.movies.domain.models.Movie

interface MemoryCacheInterface {
    fun setMovies(movies:List<Movie>)
    fun getMovies():List<Movie>?
    fun destroyMovies()
}