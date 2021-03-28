package com.example.movies.data.repos.movies

import com.example.movies.data.Resource
import com.example.movies.data.local.moviesFileManager.MOVIES_ERROR_Causes
import com.example.movies.data.memory.MemoryCacheInterface
import com.example.movies.domain.models.Movie
import java.util.*
import javax.inject.Inject

class MoviesMemoryClient @Inject constructor(
    private val memoryCacheInterface: MemoryCacheInterface,
    private val errorManager: MoviesErrorManager
) {
    /**
     * Saving the movies sorted by title to make the search operation faster*/
    fun passMoviesToMemory(movies: List<Movie>) =
        memoryCacheInterface.setMovies(movies.sortedBy { it.title })


    fun getMoviesFromMemory(): Resource<List<Movie>> {
        return memoryCacheInterface.getMovies()?.let { movies ->
            if (movies.isNotEmpty())
                Resource.Success(movies)
            else
                Resource.Error(
                    errorManager.getErrorMessage(MOVIES_ERROR_Causes.EMPTY_MOVIES_LIST),
                    MOVIES_ERROR_Causes.EMPTY_MOVIES_LIST
                )
        } ?: kotlin.run {
            Resource.Error(
                errorManager.getErrorMessage(MOVIES_ERROR_Causes.NULL_EXCEPTION),
                MOVIES_ERROR_Causes.NULL_EXCEPTION
            )
        }
    }

    fun removeMoviesFromMemory() = memoryCacheInterface.destroyMovies()

    fun searchMovies(query: String): Resource<List<Movie>> {
        val movies = memoryCacheInterface.getMovies()
        val lowerCaseQuery =query.toLowerCase(Locale.ROOT)
        return movies?.let {
            Resource.Success(movies.filter { it.title.toLowerCase(Locale.ROOT).contains(lowerCaseQuery) })
        }
            ?: kotlin.run {
                Resource.Error(
                    errorManager.getErrorMessage(MOVIES_ERROR_Causes.NULL_EXCEPTION),
                    MOVIES_ERROR_Causes.NULL_EXCEPTION
                )
            }
    }


}