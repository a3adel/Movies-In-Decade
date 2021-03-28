package com.example.movies.presentation.searchMovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movies.TestDispatcher
import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.domain.useCases.SearchMoviesUseCase
import com.example.movies.getOrAwaitValue
import com.example.movies.presentation.base.Dispatcher
import com.example.movies.presentation.models.UiValidationResult
import com.example.movies.presentation.models.YearMovies
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
internal class SearchMoviesViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var dispatcher: Dispatcher
    lateinit var SUT: SearchMoviesViewModel
    lateinit var searchMoviesUseCase: SearchMoviesUseCase
    lateinit var searchMoviesInputValidator: SearchMoviesInputValidator
    val YEARS_MOVIES_LIST = listOf(
        YearMovies(
            2015, listOf(
                Values.DADDY_IS_HOME,
                Values.TAKEN_YEAR_MOVIE,
                Values.THE_MARTIAN_MOVIE,
                Values.WOMEN_IN_BLACK_YEAR_MOVIE,
                Values.BLACK_HAT_YEAR_MOVIE,

                )
        ),
        YearMovies(2011, listOf(Values.HARRY_POTTER_MOVIE))

    )

    @Before
    fun setup() {
        searchMoviesUseCase = mockk()
        searchMoviesInputValidator = mockk()
        dispatcher = TestDispatcher()
        SUT = SearchMoviesViewModel(searchMoviesUseCase, searchMoviesInputValidator, dispatcher)
    }

    @Test
    fun `should post value to years movies list when searchMovieUseCase returnse successful resource with not empty list`() =
        runBlockingTest {
            coEvery { searchMoviesUseCase.searchMovies("") } returns Resource.Success(
                YEARS_MOVIES_LIST
            )
            every { searchMoviesInputValidator.validateQuery("") } returns UiValidationResult(true)
            SUT.searchMovies("")
            Assert.assertEquals(SUT.yearsMoviesLiveData.getOrAwaitValue(), YEARS_MOVIES_LIST)
        }

    @Test
    fun `should post value to noMoviesLiveData when empty list returned`() = runBlockingTest {
        coEvery { searchMoviesUseCase.searchMovies("") } returns Resource.Success(ArrayList())
        every { searchMoviesInputValidator.validateQuery("") } returns UiValidationResult(true)
        SUT.searchMovies("")
        Assert.assertEquals(SUT.noSearchResultsLiveData.getOrAwaitValue(), Unit)
    }

    @Test
    fun `should post value to invalid query input if the query is emoty`() = runBlockingTest {
        coVerify(exactly = 0) { searchMoviesUseCase.searchMovies(any()) }
        every { searchMoviesInputValidator.validateQuery("") } returns UiValidationResult(false,"Please insert valid search query")
        SUT.searchMovies("")
        Assert.assertEquals(SUT.invalidQueryLiveData.getOrAwaitValue().getContentIfNotHandled(),"Please insert valid search query")
    }

}
