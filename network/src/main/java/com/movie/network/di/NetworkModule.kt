package com.test.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    internal fun provideOkHttp(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .build()
    }

    @Provides
    internal fun providesMoshiConverter() = MoshiConverterFactory.create()

    @Provides
    internal inline fun provideRx2JavaCallAdaper(): RxJava2CallAdapterFactory =
        RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())

}