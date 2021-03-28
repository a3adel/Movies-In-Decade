package com.example.movies.domain.useCases

import com.example.movies.Values
import com.example.movies.data.Resource
import com.example.movies.data.repos.movies.MoviesRepoImpl
import com.example.movies.domain.dataSources.MoviesRepo
import com.example.movies.domain.models.MoviesToMoviesByYearMapper
import com.example.movies.presentation.models.YearMovies
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class SearchMoviesUseCaseTest {

    val UN_SORTED_RATING_MOVIES = listOf(
        Values.DADDY_IS_HOME,//rating is 5
        Values.HARRY_POTTER_MOVIE,// rating is 4
        Values.THE_MARTIAN_MOVIE, // rating is 3
        Values.BLACK_HAT_MOVIE,//rating 1
        Values.WOMEN_IN_BLACK_MOVIE,//rating 2
        Values.TAKEN_MOVIE,//rating4
        Values.MATCH_MOVIE//rating 1
    )


    val YEAR_MOVIES_LIST = ArrayList<YearMovies>()
    val YEAR_MOVIE_1 = YearMovies(2011, listOf(Values.HARRY_POTTER_MOVIE))
    val YEAR_MOVIE_2 = YearMovies(
        2015, listOf(
            Values.DADDY_IS_HOME,
            Values.TAKEN_YEAR_MOVIE,
            Values.THE_MARTIAN_MOVIE,
            Values.WOMEN_IN_BLACK_YEAR_MOVIE,
            Values.BLACK_HAT_YEAR_MOVIE,

            )
    )

    val EXPECTED_YEAR_MOVIES_LIST = ArrayList<YearMovies>()


    lateinit var SUT: SearchMoviesUseCase
    lateinit var moviesToMoviesByYearMapper: MoviesToMoviesByYearMapper
    lateinit var moviesRepo: MoviesRepo

    @Before
    fun setup() {
        moviesToMoviesByYearMapper = mockk()
        moviesRepo = mockk<MoviesRepoImpl>()
        SUT = SearchMoviesUseCase(moviesRepo, moviesToMoviesByYearMapper)

        EXPECTED_YEAR_MOVIES_LIST.add(YEAR_MOVIE_1)
        EXPECTED_YEAR_MOVIES_LIST.add(YEAR_MOVIE_2)
        YEAR_MOVIES_LIST.add(YEAR_MOVIE_1)
        YEAR_MOVIES_LIST.add(YearMovies(
            2015, listOf(
                Values.DADDY_IS_HOME,
                Values.TAKEN_YEAR_MOVIE,
                Values.THE_MARTIAN_MOVIE,
                Values.WOMEN_IN_BLACK_YEAR_MOVIE,
                Values.BLACK_HAT_YEAR_MOVIE,
                Values.MATCH_YEAR_MOVIE


            )
        ))
    }

    @Test
    fun `search use case when successful search should return top 5 movies by year`() =
        runBlockingTest {
            coEvery { moviesRepo.searchMovies(any()) } returns Resource.Success(
                UN_SORTED_RATING_MOVIES
            )
            coEvery { moviesToMoviesByYearMapper.mapFrom(any()) } returns YEAR_MOVIES_LIST
            val searchResource = SUT.searchMovies("")

            Assert.assertEquals(
                EXPECTED_YEAR_MOVIES_LIST,
                ((searchResource as Resource.Success).data)
            )
        }
}