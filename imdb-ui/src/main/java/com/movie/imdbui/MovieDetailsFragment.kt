package com.movie.imdbui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.movie.imdbui.databinding.FragmentMovieBinding
import com.movie.imdbui.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val imageUrl = "https://image.tmdb.org/t/p/w500\n" +
        "eg: https://image.tmdb.org/t/p/w500/p60VSQL7usdxztIGokJPpHmKWdU.jpg"

@AndroidEntryPoint
internal class MovieDetailsFragment : Fragment() {

    @Inject
    internal lateinit var movieViewModelFactory: MovieViewModelFactory

    private lateinit var binding: FragmentMovieDetailsBinding

    private val viewModel by lazy { ViewModelProvider(requireActivity(), movieViewModelFactory).get(MovieViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = viewModel.movieDetails

        binding.apply {
            Glide.with(requireContext()).load(imageUrl).into(movieImage)

            result?.let { movie ->
                title.text = movie.originalTitle
                releaseData.text = movie.releaseDate.toString()
                rating.text = movie.voteAverage.toString()
                popularity.text = movie.popularity.toString()
                overviewTitle.text = "Overview"
                overviewDesc.text = movie.overview
            }


            backTextView.setOnClickListener {
                goBack()
            }
            backIcon.setOnClickListener {
                goBack()
            }
        }
    }

    private fun goBack() {
        fragmentManager?.popBackStackImmediate()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.movieDetails = null // Reset the movie details on fragment destroy
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MovieDetailsFragment()
    }
}