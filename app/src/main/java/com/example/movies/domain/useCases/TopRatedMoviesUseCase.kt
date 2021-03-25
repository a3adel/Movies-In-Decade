package com.example.movies.domain.useCases

import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import com.example.movies.domain.models.Movie
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(private val moviesRepo: MoviesRepo) {
    suspend fun getTopRatedMovies(): Resource<List<Movie>> {
        return when (val moviesResource = moviesRepo.getMovies()) {
            is Resource.Success -> {
                val sorted = moviesResource.data.sortedByDescending { it.rating }

                Resource.Success(sorted)
            }

            else -> {
                moviesResource
            }
        }
    }
}