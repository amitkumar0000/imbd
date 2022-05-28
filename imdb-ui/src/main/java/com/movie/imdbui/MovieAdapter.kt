package com.movie.imdbui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.movie.imdbcore.model.Movies
import com.movie.imdbcore.model.Results
import com.movie.imdbui.databinding.MovieRowBinding

private const val imageUrl = "https://image.tmdb.org/t/p/w500\n" +
        "eg: https://image.tmdb.org/t/p/w500/p60VSQL7usdxztIGokJPpHmKWdU.jpg"  // This hardcoded will be removed once proper api response
internal class MovieAdapter(private val listener: (Results) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = arrayListOf<Results>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder((MovieRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        Glide.with(holder.titleTextView.context).load(imageUrl).into(holder.thumbnailImageView)

        holder.titleTextView.text = movieList[position].title
        holder.subtitleTextView.text = movieList[position].overview

        holder.thumbnailImageView.setOnClickListener {
            listener(movieList[position])   // on click send a callback with movie information
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateList(newList: List<Results>) {
        movieList.addAll(newList)
        notifyDataSetChanged() // TODO("Optimized it with Diff Utils")
    }
}