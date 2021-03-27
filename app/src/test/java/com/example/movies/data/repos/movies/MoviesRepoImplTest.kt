package com.example.movies.data.repos.movies

import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.domain.dataSources.MoviesRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
@ExperimentalCoroutinesApi
internal class MoviesRepoImplTest {
    lateinit var movieLocalClient: MoviesLocalClient
    lateinit var moviesMemoryClient: MoviesMemoryClient
    lateinit var SUT: MoviesRepo

    @Before
    fun setup() {
        movieLocalClient = mockk()
        moviesMemoryClient = mockk(relaxed = true)
        SUT = MoviesRepoImpl(movieLocalClient,moviesMemoryClient)
    }

    @Test
    fun `getMovies call when sucess should call pass movies to memory`() = runBlockingTest {
        coEvery { movieLocalClient.getMovies() } returns Resource.Success(Values.UN_SORTED_RATING_MOVIES)
        SUT.getMovies()

        coVerify(exactly = 1) { movieLocalClient.getMovies() }
        coVerify (exactly = 1){ moviesMemoryClient.passMoviesToMemory(any()) }
    }

    @Test
    fun `getMovies call when error should not call pass movies to memory`() = runBlockingTest {
        coEvery { movieLocalClient.getMovies() } returns Resource.Error("","")
        SUT.getMovies()

        coVerify(exactly = 1) { movieLocalClient.getMovies() }
        coVerify (exactly = 0){ moviesMemoryClient.passMoviesToMemory(any()) }
    }

    @Test
    fun `searchmovies call should call moviesMemoryClient searchMovies`() = runBlockingTest {
        coEvery { movieLocalClient.getMovies() } returns Resource.Success(Values.UN_SORTED_RATING_MOVIES)
        SUT.searchMovies("")

        coVerify (exactly = 1){ moviesMemoryClient.searchMovies(any()) }
    }

}