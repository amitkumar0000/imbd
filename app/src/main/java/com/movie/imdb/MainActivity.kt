package com.movie.imdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.movie.imdb.databinding.ActivityMainBinding
import com.movie.imdbui.MovieFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        attachMovieFragment()
    }

    private fun attachMovieFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, MovieFragment.newInstance(), "movieFragment")
            .commit()
    }
}