package com.example.movies.data.repos.movies

import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.data.local.moviesFileManager.FileResponse
import com.example.movies.data.local.moviesFileManager.MoviesFileInterface
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MoviesLocalClientTest {

    lateinit var moviesFileInterface: MoviesFileInterface
    lateinit var movieEntityToMovieMapper: MovieEntityToMovieMapper
    lateinit var moviesErrorManager: MoviesErrorManager
    lateinit var SUT: MoviesLocalClient

    @Before
    fun setup() {
        moviesFileInterface = mockk()
        movieEntityToMovieMapper = mockk()
        moviesErrorManager = mockk()
        SUT = MoviesLocalClient(moviesFileInterface, movieEntityToMovieMapper,moviesErrorManager)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get movies should return resource of list of movies`() = runBlockingTest {
        coEvery { moviesFileInterface.getMovies() } returns FileResponse.FileText(Values.MOVIE_ENTITIES_LIST)
        every { movieEntityToMovieMapper.mapFrom(any()) } returns Values.HARRY_POTTER_MOVIE
        val moviesReource = SUT.getMovies()
        Assert.assertEquals(moviesReource.javaClass.name,Resource.Success::class.java.name)
        val movies = (moviesReource as Resource.Success).data
        Assert.assertEquals(2,movies.size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get movies should return resource of error`()= runBlockingTest {
        coEvery { moviesFileInterface.getMovies() } returns FileResponse.Error("","")
        every { movieEntityToMovieMapper.mapFrom(any()) } returns Values.HARRY_POTTER_MOVIE
        every { moviesErrorManager.getErrorMessage(any()) } returns ""
        verify(exactly = 0) {movieEntityToMovieMapper.mapFrom(any())  }
        val moviesReource = SUT.getMovies()
        Assert.assertEquals(moviesReource.javaClass.name, Resource.Error::class.java.name)
    }
}