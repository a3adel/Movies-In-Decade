package com.example.movies.domain.useCases

import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import com.example.movies.domain.models.MoviesToMoviesByYearMapper
import com.example.movies.presentation.models.YearMovies
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val moviesRepo: MoviesRepo,
    private val moviesToMoviesByYearMapper: MoviesToMoviesByYearMapper
) {
    suspend fun searchMovies(query: String): Resource<List<YearMovies>> =
        when (val moviesResource = moviesRepo.searchMovies(query)) {
            is Resource.Success -> {
                val yearMovies = moviesToMoviesByYearMapper.mapFrom(moviesResource.data) as ArrayList<YearMovies>
                for (yearMovie in yearMovies) {
                    yearMovie.movies = yearMovie.movies.take(5)
                    val index= yearMovies.indexOf(yearMovie)
                    yearMovies.set(index,yearMovie)
                }
                Resource.Success(yearMovies)
            }
            is Resource.Error -> Resource.Error(
                moviesResource.message,
                moviesResource.cause
            )


        }
}