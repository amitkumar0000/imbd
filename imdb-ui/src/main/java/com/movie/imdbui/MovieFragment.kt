package com.movie.imdbui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.movie.imdbcore.model.MoviesState
import com.movie.imdbcore.model.Results
import com.movie.imdbui.databinding.FragmentMovieBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieFragment : Fragment() {

    @Inject
    internal lateinit var movieViewModelFactory: MovieViewModelFactory

    private lateinit var binding: FragmentMovieBinding

    private val disposable by lazy { CompositeDisposable() }

    private val movieAdapter by lazy { MovieAdapter { movie ->
            loadMovieDetailFragment(movie)
        }
    }

    private val viewModel by lazy { ViewModelProvider(requireActivity(), movieViewModelFactory).get(MovieViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMovieListLayout()

        observeViewModel()

        fetchMovieList()
    }

    private fun initMovieListLayout() {
        binding.movieList.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeViewModel() {
        disposable.addAll(
            viewModel.state()
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       when(it) {
                           is MoviesState.Loading -> {
                               setLoaderViisible(true)
                           }
                           is MoviesState.Success -> {
                               setLoaderViisible(false)
                               movieAdapter.updateList(it.movies)
                           }
                           is MoviesState.Error -> {
                               setLoaderViisible(false)
                           }
                       }
            }, {})
        )
    }

    private fun setLoaderViisible(isVisible: Boolean) {
        binding.loader.isVisible = isVisible
    }

    private fun fetchMovieList() {
        viewModel.fetchMovieList()
    }

    private fun loadMovieDetailFragment(movie: Results) {

        viewModel.movieDetails = movie

        childFragmentManager.beginTransaction()
            .replace(R.id.movieDetailsFragment, MovieDetailsFragment.newInstance(), "movieDeatilsFragment")
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}