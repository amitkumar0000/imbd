package com.movie.imdbui.di

import android.content.Context
import com.movie.imdbcore.source.JsonFileSource
import com.movie.imdbcore.source.MovieApiService
import com.movie.imdbui.JsonFileSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class MovieModules {
    private val baseUrl = "https://image.tmdb.org/"
    @Provides
    fun providesMovieJsonFileSource(@ApplicationContext context: Context): JsonFileSource {
        return JsonFileSourceImpl(context = context)
    }

    @Provides
    internal fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .build()

    @Provides
    internal fun provideMovieApiSource(retrofit: Retrofit) = retrofit.create(MovieApiService::class.java)
}