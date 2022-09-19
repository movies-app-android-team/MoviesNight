package com.example.moviesnight.`interface`

import com.example.moviesnight.model.DetailedMovie

fun interface DetailedMovieCallback {
    fun onMovieReady(movie: DetailedMovie)
}