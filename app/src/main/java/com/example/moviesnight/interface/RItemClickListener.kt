package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.recycler.Movie

interface RItemClickListener {
    fun onRMovieItemClick(view: View, movieItem: Moviee)
}