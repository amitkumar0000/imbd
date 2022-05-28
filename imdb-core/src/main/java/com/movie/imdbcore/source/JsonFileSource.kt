package com.movie.imdbcore.source

import com.movie.imdbcore.model.Movies
import io.reactivex.Single

interface JsonFileSource {
    /**
     * Fetch movie list.
     *
     * @return [Single]
     */
    fun fetchMovieList(): Single<Movies>
}