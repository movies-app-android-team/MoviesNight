package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.bookmarks.BookmarkMovieItem
import com.example.moviesnight.models.Moviee

interface BItemClickListener {
    fun onBMovieItemClick(view: View, movieItem: Moviee)
}