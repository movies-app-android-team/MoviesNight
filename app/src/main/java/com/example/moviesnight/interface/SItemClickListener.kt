package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.slider.Movie

interface SItemClickListener {
    fun onSMovieItemClick(view: View, movieItem: Moviee)
}