package com.example.moviesnight.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class MoviesGenreResponse(
    @SerializedName("results")
    val moviesGenre : List<MoviesGenre>
) : Parcelable {
    constructor() : this(mutableListOf())
}