package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.recycler.RMovieItem

interface RItemClickListener {
    fun onRMovieItemClick(view: View, movieItem: RMovieItem)
}