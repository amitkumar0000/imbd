package com.movie.imdbcore.model

sealed class MoviesState {
    object Loading: MoviesState()
    data class Error(val msg: String): MoviesState()
    data class Success(val movies: List<Results>): MoviesState()
}