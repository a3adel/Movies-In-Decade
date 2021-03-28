package com.example.movies.domain.models

import com.example.movies.data.base.Mapper
import com.example.movies.presentation.models.YearMovies
import javax.inject.Inject

/**
 *This mapper class transforms List<Movie> to List<YearMovies>
 *     a list of grouped movies by year
 *     it uses another mapper MovieToYearMovieMapper
 *     which maps Movie object to YearMovie object
 */

class MoviesToMoviesByYearMapper @Inject constructor() :
    Mapper<List<Movie>, List<YearMovies>>() {
    override fun mapFrom(movies: List<Movie>): List<YearMovies> {
        val moviesByYear = ArrayList<YearMovies>()
        val groupedByYear = movies.groupBy { movie -> movie.year }
        for (key in groupedByYear.keys) {
            groupedByYear[key]
                ?.let { groupedMovies ->
                    val yearMovie = YearMovies(key, groupedMovies.sortedByDescending { it.rating })
                    moviesByYear.add(yearMovie)
                }
        }
        return moviesByYear
    }
}