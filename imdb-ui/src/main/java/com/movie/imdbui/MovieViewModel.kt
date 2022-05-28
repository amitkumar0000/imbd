package com.movie.imdbui

import androidx.lifecycle.ViewModel
import com.movie.imdbcore.model.MoviesState
import com.movie.imdbcore.model.Results
import com.movie.imdbcore.repository.MovieRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

internal class MovieViewModel @Inject constructor(private val repository: MovieRepository): ViewModel() {

    private val state = BehaviorSubject.create<MoviesState>()

    private val disposable by lazy { CompositeDisposable() }

    var movieDetails: Results? = null

    fun state() = state.hide()

    fun fetchMovieList() {
        state.onNext(MoviesState.Loading)
        disposable.addAll(repository.fetchMovieListFromJsonFile()
            .subscribeOn(Schedulers.io())
            .subscribe({
                 state.onNext(MoviesState.Success(it.results))
            },{
                state.onNext(MoviesState.Error("${it.localizedMessage}"))
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}