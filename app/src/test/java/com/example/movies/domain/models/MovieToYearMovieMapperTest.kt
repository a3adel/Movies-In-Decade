package com.example.movies.domain.models

import com.example.movies.Values
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class MovieToYearMovieMapperTest {
    lateinit var SUT: MovieToYearMovieMapper

    @Before
    fun setup() {
        SUT = MovieToYearMovieMapper()
    }

    @Test
    fun `mapFrom function should map Movie to YearMovie Object`(){
        val yearMovie = SUT.mapFrom(Values.HARRY_POTTER_MOVIE)
        Assert.assertEquals(yearMovie,Values.HARRY_POTTER_YEAR_MOVIE)
    }
}