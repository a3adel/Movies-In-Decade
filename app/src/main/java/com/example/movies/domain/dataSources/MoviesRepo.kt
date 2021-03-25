package com.example.movies.domain.dataSources

import com.example.movies.data.Resource
import com.example.movies.domain.models.Movie

interface MoviesRepo {
    suspend fun getMovies(): Resource<List<Movie>>
}