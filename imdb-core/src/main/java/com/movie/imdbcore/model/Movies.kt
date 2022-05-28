package com.movie.imdbcore.model


// TODO("For release build proguard rules or @Serializename will be added")
data class Movies(
    var dates: Dates? = null,
    var page: Int? = null,
    var results: ArrayList<Results> = arrayListOf(),
    var totalPages: Int? = null,
    var totalResults: Int? = null
)