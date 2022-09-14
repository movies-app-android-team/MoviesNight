package com.example.moviesnight.`interface`

import android.view.View
import com.example.moviesnight.bookmarks.BookmarkMovieItem

interface BItemClickListener {
    fun onBMovieItemClick(view: View, movieItem: BookmarkMovieItem)
}