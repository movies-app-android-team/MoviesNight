package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.model.Movie

interface MovieClickListener {
    fun onMovieItemClick(view: View, movieItem: Movie)
}