package com.example.movies.data.repos.movies

import com.example.movies.data.Resource
import com.example.movies.data.local.moviesFileManager.FileResponse
import com.example.movies.data.local.moviesFileManager.MoviesFileInterface
import com.example.movies.domain.models.Movie
import javax.inject.Inject

class MoviesLocalClient @Inject constructor(
    private val moviesFileInterface: MoviesFileInterface,
    private val movieMapper: MovieEntityToMovieMapper,
    private val errorManager: MoviesErrorManager
) {
    suspend fun getMovies(): Resource<List<Movie>> =
        when (val response = moviesFileInterface.getMovies()) {
            is FileResponse.FileText -> {
                val movies = ArrayList<Movie>()
                for (movie in response.data)
                    movies.add(movieMapper.mapFrom(movie))
                Resource.Success(movies)
            }
            is FileResponse.Error -> {
                Resource.Error(errorManager.getErrorMessage(response.cause), response.cause)
            }
        }

}