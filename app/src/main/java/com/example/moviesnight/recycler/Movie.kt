package com.example.moviesnight.recycler

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val id: String?,

    @SerializedName("poster_path")
    val poster: String?,

    var isBookmarked: Boolean = false
) : Parcelable