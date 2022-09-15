package com.example.moviesnight.bookmarks

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class BookmarkMovieItem(
    val movieId: Int,
    val movieImage: Int,
    val movieName: String,
    val movieGenre: String,
    val movieRating: Float,
    var isBookmarked: Boolean = false
): Parcelable