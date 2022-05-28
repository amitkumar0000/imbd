package com.movie.imdbcore.source

import com.movie.imdbcore.model.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApiService {
    /**
     * Get movie list from network.
     *
     * @return [Single]
     */
    @Headers("api-key: 38a73d59546aa378980a88b645f487fc")
    @GET("/t/p/w500/p60VSQL7usdxztIGokJPpHmKWdU.jpg")
    fun getMovieList(): Single<Movies>

}