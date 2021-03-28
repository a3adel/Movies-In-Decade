package com.example.movies.presentation.models

import com.example.movies.domain.models.Movie

data class YearMovies(val year: Int, var movies: List<Movie>)