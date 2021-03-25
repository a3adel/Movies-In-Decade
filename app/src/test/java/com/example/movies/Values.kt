package com.example.movies

import com.example.movies.data.entities.MovieEntity
import com.example.movies.domain.models.Movie

object Values {
    val THE_MARTIAN_MOVIE_ENTITY = MovieEntity(
        title = "the martian",
        year = 2015,
        cast = listOf("Matt Damon"),
        genres = listOf("Science Fiction"),
        rating = 3f
    )
    val HARRY_POTTER_MOVIE_ENTITY = MovieEntity(
        title = "harry potter",
        year = 2011,
        cast = listOf(
            "Daniel Radcliffe",
            "Rupert Grint",
            "Emma Watson"
        ),
        genres = listOf(
            "Action",
            "Adventure",
            "Fantasy"
        ),
        rating = 4f

    )

    val THE_MARTIAN_MOVIE = Movie(
        title = "the martian",
        year = 2015,
        cast = listOf("Matt Damon"),
        genres = listOf("Science Fiction"),
        rating = 3f
    )
    val HARRY_POTTER_MOVIE = Movie(
        title = "harry potter",
        year = 2011,
        cast = listOf(
            "Daniel Radcliffe",
            "Rupert Grint",
            "Emma Watson"
        ),
        genres = listOf(
            "Action",
            "Adventure",
            "Fantasy"
        ),
        rating = 4f

    )

    val DADDY_IS_HOME = Movie(
        title = "Daddy's Home",
        year = 2015,
        cast = listOf(
            "Mark Wahlberg",
            "Will Ferrell",
            "Linda Cardellini"
        ),
        rating = 5f,
        genres = listOf("Comedy")
    )


    val MOVIE_ENTITIES_LIST = listOf<MovieEntity>(
        HARRY_POTTER_MOVIE_ENTITY,
        THE_MARTIAN_MOVIE_ENTITY
    )
    val MOVIES_LIST = listOf<Movie>(
        HARRY_POTTER_MOVIE,
        THE_MARTIAN_MOVIE
    )

    val UN_SORTED_RATING_MOVIES = listOf(
        HARRY_POTTER_MOVIE,
        DADDY_IS_HOME,
        THE_MARTIAN_MOVIE
    )

    val SORTED_RATING_MOVIES = listOf(
        DADDY_IS_HOME,//rating is 5
        HARRY_POTTER_MOVIE,// rating is 4
        THE_MARTIAN_MOVIE // rating is 3
    )
}