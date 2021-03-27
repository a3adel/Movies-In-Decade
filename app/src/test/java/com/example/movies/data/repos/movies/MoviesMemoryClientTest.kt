package com.example.movies.data.repos.movies

import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.data.local.moviesFileManager.Causes
import com.example.movies.data.memory.MemoryCacheInterface
import com.example.movies.domain.models.Movie
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.ArgumentMatchers.any

@ExperimentalCoroutinesApi
internal class MoviesMemoryClientTest {
    lateinit var SUT: MoviesMemoryClient
    lateinit var memoryCacheInterface: MemoryCacheInterface
    lateinit var errorManager: MoviesErrorManager

    @Before
    fun setUp() {
        memoryCacheInterface = mockk()
        errorManager = mockk(relaxed = true)
        SUT = MoviesMemoryClient(memoryCacheInterface,errorManager)
    }

    @Test
    fun `get movies should return successful resource object`() = runBlockingTest {
        every { memoryCacheInterface.getMovies() } returns Values.UN_SORTED_RATING_MOVIES

        val moviesResource = SUT.getMoviesFromMemory()
        Assert.assertEquals(moviesResource.javaClass.name, Resource.Success::class.java.name)
        assertEquals((moviesResource as Resource.Success).data, Values.UN_SORTED_RATING_MOVIES)
    }

    @Test
    fun `get movies when empty list should return error resource object`() = runBlockingTest {
        every { memoryCacheInterface.getMovies() } returns ArrayList()

        val moviesResource = SUT.getMoviesFromMemory()
        Assert.assertEquals(moviesResource.javaClass.name, Resource.Error::class.java.name)
        assertEquals((moviesResource as Resource.Error).cause, Causes.EMPTY_MOVIES_LIST)
    }

    @Test
    fun `get movies when null list should return error resource object`() = runBlockingTest {
        every { memoryCacheInterface.getMovies() } returns null

        val moviesResource = SUT.getMoviesFromMemory()
        Assert.assertEquals(moviesResource.javaClass.name, Resource.Error::class.java.name)
        assertEquals((moviesResource as Resource.Error).cause, Causes.NULL_EXCEPTION)
    }



    @Test
    fun `search movies should return successful resource object`() = runBlockingTest {
        every { memoryCacheInterface.getMovies() } returns Values.SORTED_RATING_MOVIES

        val moviesResource = SUT.searchMovies("rr")
        Assert.assertEquals(moviesResource.javaClass.name, Resource.Success::class.java.name)
        assertEquals((moviesResource as Resource.Success).data, Values.FILTERED_LIST)
    }



    @Test
    fun `search movies when null list should return error resource object`() = runBlockingTest {
        every { memoryCacheInterface.getMovies() } returns null

        val moviesResource = SUT.searchMovies("")
        Assert.assertEquals(moviesResource.javaClass.name, Resource.Error::class.java.name)
        assertEquals((moviesResource as Resource.Error).cause, Causes.NULL_EXCEPTION)
    }



}