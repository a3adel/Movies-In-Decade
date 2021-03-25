package com.example.movies.data.repos.movies

import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import com.example.movies.domain.models.Movie
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(private val moviesLocalClient: MoviesLocalClient) :
    MoviesRepo {
    override suspend fun getMovies(): Resource<List<Movie>> {
        return moviesLocalClient.getMovies()
    }
}