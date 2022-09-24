package com.example.moviesnight

import com.example.moviesnight.models.Moviee


fun interface MovieCallback {
    fun onMoviesReady(movies: List<Moviee>?)

}