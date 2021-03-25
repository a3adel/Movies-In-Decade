package com.example.movies.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies.TestDispatcher
import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.domain.useCases.TopRatedMoviesUseCase
import com.example.movies.getOrAwaitValue
import com.example.movies.presentation.base.Dispatcher
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
internal class MoviesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var topRatedMoviesUseCase: TopRatedMoviesUseCase
    lateinit var dispatcher: Dispatcher
    lateinit var SUT: MoviesViewModel

    @Before
    fun setup() {
        topRatedMoviesUseCase = mockk()
        dispatcher = TestDispatcher()
        SUT = MoviesViewModel(topRatedMoviesUseCase, dispatcher)
    }

    @Test
    fun `get top rated movies should post list to moviesLiveData`() = runBlockingTest {
        coEvery { topRatedMoviesUseCase.getTopRatedMovies() } returns Resource.Success(Values.SORTED_RATING_MOVIES)
        SUT.getMovies()
        assertEquals(SUT.moviesListLiveData.getOrAwaitValue(), Values.SORTED_RATING_MOVIES)
    }

    @Test
    fun `get top rated movies should post error message as toast`() = runBlockingTest {
        coEvery { topRatedMoviesUseCase.getTopRatedMovies() } returns Resource.Error(
            "error message",
            "error cause"
        )
        SUT.getMovies()
        assertEquals(SUT.toastLiveData.getOrAwaitValue().getContentIfNotHandled(), "error message")
    }
}