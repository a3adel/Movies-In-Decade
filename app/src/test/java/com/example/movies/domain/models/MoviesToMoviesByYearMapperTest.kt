package com.example.movies.domain.models

import com.example.movies.Values
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class MoviesToMoviesByYearMapperTest {
    lateinit var SUT: MoviesToMoviesByYearMapper
    lateinit var mapper: MovieToYearMovieMapper
    @Before
    fun setUp() {
        mapper = MovieToYearMovieMapper()
        SUT = MoviesToMoviesByYearMapper(mapper)
    }

    @Test
    fun `map list of movies to list of YearMovies`(){
        val yearMovies = SUT.mapFrom(Values.SORTED_RATING_MOVIES)
        Assert.assertEquals(yearMovies,Values.YEAR_MOVIES_LIST)
    }
}