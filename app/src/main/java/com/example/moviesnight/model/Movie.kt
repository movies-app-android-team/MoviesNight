package com.example.moviesnight.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val movieID: Int,

    @SerializedName("poster_path")
    val posterPath: String?
) : Parcelable