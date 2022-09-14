package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.slider.SMovieItem

interface SItemClickListener {
    fun onSMovieItemClick(view: View, movieItem: SMovieItem)
}