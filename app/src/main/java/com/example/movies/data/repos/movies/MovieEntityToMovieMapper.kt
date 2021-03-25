package com.example.movies.data.repos.movies

import com.example.movies.data.base.Mapper
import com.example.movies.data.entities.MovieEntity
import com.example.movies.domain.models.Movie
import javax.inject.Inject

class MovieEntityToMovieMapper @Inject constructor() : Mapper<MovieEntity, Movie>() {
    override fun mapFrom(entity: MovieEntity): Movie {
        return Movie(
            title = entity.title,
            year = entity.year,
            cast = entity.cast,
            genres = entity.genres,
            rating = entity.rating
        )
    }
}