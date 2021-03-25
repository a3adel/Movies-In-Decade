package com.example.movies.domain.useCases

import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class TopRatedMoviesUseCaseTest {
    lateinit var moviesRepo: MoviesRepo
    lateinit var SUT: TopRatedMoviesUseCase

    @Before
    fun setup() {
        moviesRepo = mockk()
        SUT = TopRatedMoviesUseCase(moviesRepo)
    }

    @Test
    fun `get top rated movies returns movies sorted by rating`() = runBlockingTest {
        coEvery { moviesRepo.getMovies() } returns Resource.Success(Values.UN_SORTED_RATING_MOVIES)
        val sortedResource = SUT.getTopRatedMovies()
        Assert.assertEquals(sortedResource.javaClass.name, Resource.Success::class.java.name)
        Assert.assertEquals((sortedResource as Resource.Success).data, Values.SORTED_RATING_MOVIES)
    }
}