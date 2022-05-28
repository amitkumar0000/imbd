package com.movie.imdbui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movie.imdbcore.repository.MovieRepository
import javax.inject.Inject

internal class MovieViewModelFactory @Inject constructor(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java))
            return MovieViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}