package com.example.moviesnight.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id")
    val movieID: Int,

    @SerializedName("title")
    val movieTitle: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("genre_ids")
    val genres: List<Int>,

    var isBookmarked: Boolean
) : Parcelable