package com.example.movies.data.repos.movies

import com.example.movies.data.entities.MovieEntity
import com.example.movies.domain.models.Movie
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieEntityToMovieMapperTest {
    lateinit var movieEntity: MovieEntity
    lateinit var movie: Movie

    @Before
    fun setUp() {
        movieEntity = MovieEntity(
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

        movie = Movie(
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
    }

    @Test
    fun `passing movie entity object should return movie object`() {
        val SUT = MovieEntityToMovieMapper()
        val mappedMovie = SUT.mapFrom(movieEntity)
        Assert.assertEquals(mappedMovie.title,movie.title)
        Assert.assertEquals(mappedMovie.year,movie.year)
        Assert.assertEquals(mappedMovie.rating,movie.rating)
        Assert.assertEquals(mappedMovie.genres,movie.genres)
        Assert.assertEquals(mappedMovie.cast,movie.cast)
    }
}