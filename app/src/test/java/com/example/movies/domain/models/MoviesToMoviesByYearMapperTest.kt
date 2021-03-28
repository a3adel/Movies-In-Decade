package com.example.movies.domain.models

import com.example.movies.Values
import com.example.movies.presentation.models.YearMovies
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class MoviesToMoviesByYearMapperTest {
    lateinit var SUT: MoviesToMoviesByYearMapper
    @Before
    fun setUp() {
        SUT = MoviesToMoviesByYearMapper()
    }

    @Test
    fun `map list of movies to list of YearMovies`(){
        val yearMovies = SUT.mapFrom(Values.SORTED_RATING_MOVIES)
       val YEAR_MOVIES_LIST = listOf(
            YearMovies(
                2015, listOf(
                    Values.DADDY_IS_HOME, Values.THE_MARTIAN_MOVIE
                )
            ),
            YearMovies(2011, listOf(Values.HARRY_POTTER_MOVIE))

        )
        Assert.assertEquals(yearMovies,YEAR_MOVIES_LIST)
    }
}