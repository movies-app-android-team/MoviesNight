package com.example.moviesnight.recycler

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RMovieItem(val movieID: Int, val imageID: Int, var isBookmarked: Boolean = false): Parcelable