package com.example.moviesnight.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    @SerializedName("id")
    val genreID: Int,

    @SerializedName("name")
    var genreName: String

) : Parcelable