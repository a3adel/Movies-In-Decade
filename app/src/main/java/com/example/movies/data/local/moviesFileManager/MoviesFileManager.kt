package com.example.movies.data.local.moviesFileManager

import com.example.movies.data.entities.MovieEntity

interface MoviesFileManager {
    suspend fun getMovies(): FileResponse<List<MovieEntity>>
}