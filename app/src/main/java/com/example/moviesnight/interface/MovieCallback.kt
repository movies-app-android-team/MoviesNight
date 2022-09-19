package com.example.moviesnight.`interface`

import com.example.moviesnight.model.Movie

fun interface MovieCallback {
    fun onMoviesReady(movies: List<Movie>?)
}