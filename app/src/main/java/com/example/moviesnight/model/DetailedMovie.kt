package com.example.moviesnight.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedMovie(
    @SerializedName("id")
    val movieID: Int,

    @SerializedName("title")
    val movieTitle: String,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("runtime")
    val runTime: Int?,

    @SerializedName("release_date")
    val year: String,

    @SerializedName("overview")
    val overview: String?,

    var isBookmarked: Boolean
) : Parcelable