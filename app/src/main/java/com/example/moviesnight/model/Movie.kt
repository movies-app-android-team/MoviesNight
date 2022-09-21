package com.example.moviesnight.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.paperdb.Paper
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
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

    @SerializedName("genre_ids")
    val genres: List<Int>,

    var isBookmarked: Boolean= Paper.book().read<Int>("${movieID}")==1
) : Parcelable