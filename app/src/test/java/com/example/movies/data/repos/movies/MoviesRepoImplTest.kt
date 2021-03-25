package com.example.movies.data.repos.movies

import com.example.movies.domain.dataSources.MoviesRepo
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

internal class MoviesRepoImplTest {
    lateinit var movieLocalClient: MoviesLocalClient
    lateinit var SUT :MoviesRepo
    @Before
    fun setup() {
        movieLocalClient = mockk(relaxed = true)
        SUT = MoviesRepoImpl(movieLocalClient)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `getMovies call should call movieLocalClient get movies`() = runBlockingTest {
        SUT.getMovies()
        coVerify(exactly = 1) { movieLocalClient.getMovies() }
    }
}