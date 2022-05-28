package com.movie.imdbcore.source

import com.movie.imdbcore.model.Movies
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApiService {
    /**
     * Get movie list from network.
     *
     * @return [Single]
     */
    @GET("/t/p/w500/p60VSQL7usdxztIGokJPpHmKWdU.jpg")
    fun getMovieList(): Single<Movies>

}