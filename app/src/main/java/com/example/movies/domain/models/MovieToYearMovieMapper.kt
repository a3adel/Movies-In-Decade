package com.example.movies.domain.models

import com.example.movies.data.base.Mapper
import com.example.movies.presentation.models.YearMovie
import javax.inject.Inject

class MovieToYearMovieMapper @Inject constructor() : Mapper<Movie, YearMovie>() {
    override fun mapFrom(movie: Movie): YearMovie {
        return YearMovie(
            movie.title,
            cast = movie.cast,
            genres = movie.genres,
            rating = movie.rating
        )
    }
}