package com.movie.imdbcore.repository

import com.movie.imdbcore.model.Movies
import com.movie.imdbcore.source.JsonFileSource
import com.movie.imdbcore.source.MovieApiService
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(private val jsonFileSource: JsonFileSource, private val movieApiService: MovieApiService) {
    fun fetchMovieListFromJsonFile(): Single<Movies> {
        return jsonFileSource.fetchMovieList()
    }

    fun getMovieListFromNetwork(): Single<Movies> {
        return movieApiService.getMovieList()
    }
}