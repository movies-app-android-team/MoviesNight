package com.example.moviesnight.models

fun interface MoviesGenreCallback {
    fun onMoviesGenresReady(moviesGenres: List<MoviesGenre>?)
}