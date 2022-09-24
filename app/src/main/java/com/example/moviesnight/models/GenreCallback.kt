package com.example.moviesnight.models

fun interface GenreCallback {
    fun onGenresReady(movies: List<Genre>?)
}