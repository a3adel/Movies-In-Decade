package com.example.movies

import com.example.movies.data.entities.MovieEntity
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.models.YearMovies

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






    val WOMEN_IN_BLACK_MOVIE = Movie(
        title = "The Woman in Black 2: Angel of Death",
        year = 2015,
        cast = listOf(
            "Phoebe Fox",
            "Jeremy Irvine",
            "Helen McCrory",
            "Adrian Rawlins",
            "Ned Dennehy"
        ),
        genres = listOf("Horror"),
        rating = 2f
    )
    val TAKEN_MOVIE = Movie(
        title = "Taken 3",
        year = 2015,
        cast = listOf(
            "Liam Neeson",
            "Forest Whitaker",
            "Famke Janssen",
            "Maggie Grace"
        ),
        genres = listOf("Action"),
        rating = 4f
    )

    val MATCH_MOVIE = Movie(
        title = "Match",
        year = 2015,
        cast = listOf(
            "Patrick Stewart",
            "Carla Gugino",
            "Matthew Lillard",
            "Rob Yang"
        ),
        genres = listOf("Drama"),
        rating = 2f
    )

    val BLACK_HAT_MOVIE = Movie(
        title = "blackHat",
        year = 2015,
        cast = listOf(
            "Chris Hemsworth",
            "Viola Davis",
            "Manny Montana",
            "Tang Wei"
        ),
        genres = listOf("Action"),
        rating = 1f
    )



    val WOMEN_IN_BLACK_YEAR_MOVIE = Movie(
        title = "The Woman in Black 2: Angel of Death",
        cast = listOf(
            "Phoebe Fox",
            "Jeremy Irvine",
            "Helen McCrory",
            "Adrian Rawlins",
            "Ned Dennehy"
        ),
        genres = listOf("Horror"),
        rating = 2f,
        year = 2010
    )
    val TAKEN_YEAR_MOVIE = Movie(
        title = "Taken 3",
        cast = listOf(
            "Liam Neeson",
            "Forest Whitaker",
            "Famke Janssen",
            "Maggie Grace"
        ),
        genres = listOf("Action"),
        rating = 4f,year = 2012
    )

    val MATCH_YEAR_MOVIE = Movie(
        title = "Match",
        cast = listOf(
            "Patrick Stewart",
            "Carla Gugino",
            "Matthew Lillard",
            "Rob Yang"
        ),
        genres = listOf("Drama"),
        rating = 1f,
        year = 2010
    )

    val BLACK_HAT_YEAR_MOVIE = Movie(
        title = "blackHat",
        cast = listOf(
            "Chris Hemsworth",
            "Viola Davis",
            "Manny Montana",
            "Tang Wei"
        ),
        genres = listOf("Action"),
        rating = 2f,
        year = 2011
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

    val YEAR_MOVIES_LIST = listOf(
        YearMovies(
            2015, listOf(
                DADDY_IS_HOME, THE_MARTIAN_MOVIE, TAKEN_YEAR_MOVIE,
                MATCH_YEAR_MOVIE, WOMEN_IN_BLACK_YEAR_MOVIE
            )
        ),
        YearMovies(2011, listOf(HARRY_POTTER_MOVIE))

    )
    val FILTERED_LIST = listOf(HARRY_POTTER_MOVIE)
}