package com.movie.imdbui

import androidx.recyclerview.widget.RecyclerView
import com.movie.imdbui.databinding.MovieRowBinding

internal class MovieViewHolder(binding: MovieRowBinding): RecyclerView.ViewHolder(binding.root) {
    val thumbnailImageView = binding.thumbnail
    val titleTextView = binding.title
    val subtitleTextView = binding.subtitle
}