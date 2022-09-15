package com.example.moviesnight.slider

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SMovieItem(val movieID: Int, val imageID: Int, var isBookmarked: Boolean = false): Parcelable