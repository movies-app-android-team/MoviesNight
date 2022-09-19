package com.example.moviesnight.`interface`

import com.example.moviesnight.model.Genre

fun interface GenreCallback {
    fun onGenreReady(genres: List<Genre>?)
}