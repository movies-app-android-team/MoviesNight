package com.example.moviesnight.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse (

    @SerializedName("results")
    val movies : List<Moviee>
) : Parcelable{
    constructor() : this(mutableListOf())
}