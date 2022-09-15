package com.example.moviesnight

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassedMovie(val movieID: Int, var isBookmarked: Boolean): Parcelable