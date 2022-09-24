package com.example.moviesnight.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class GenreResponse(

    @SerializedName("genres")
    val genres : List<Genre>)

    : Parcelable {
    constructor() : this(mutableListOf())
}