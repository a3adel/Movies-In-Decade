package com.example.movies.data.repos.movies

import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import com.example.movies.domain.models.Movie
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val moviesLocalClient: MoviesLocalClient,
    private val moviesMemoryClient: MoviesMemoryClient
) :
    MoviesRepo {
    override suspend fun getMovies(): Resource<List<Movie>> {
        val moviesResource = moviesLocalClient.getMovies()
        when(moviesResource){
            is Resource.Success->{
                moviesMemoryClient.passMoviesToMemory(moviesResource.data)
            }
        }
        return moviesResource
    }

    override suspend fun searchMovies(query: String): Resource<List<Movie>> {
        return moviesMemoryClient.searchMovies(query)
    }
}