package com.movie.imdbui

import android.content.Context
import com.movie.imdbcore.model.Movies
import com.movie.imdbcore.source.JsonFileSource
import com.movie.imdbui.utils.JsonFileReader
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import javax.inject.Inject

internal class JsonFileSourceImpl @Inject constructor(@ApplicationContext private val context: Context): JsonFileSource {
    override fun fetchMovieList(): Single<Movies> {
        return Single.just(JsonFileReader.getObjectFromJsonFile<Movies>(
            context,
            "movies.json"
        ))
    }
}