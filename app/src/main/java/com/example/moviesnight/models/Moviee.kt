package com.example.moviesnight.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Moviee(
    @SerializedName("id")
    val id : Int ?,

    @SerializedName("title")
    val title : String?,

/*    @SerializedName("genre_ids")
    val genre_ids : String ,*/

    @SerializedName("poster_path")
    val poster : String?,

    @SerializedName("vote_count")
    val ratings : String?,

    @SerializedName("genre")
    val genre : Int,

    @SerializedName("overview")
    val overview : String,

    @SerializedName("release_date")
    val release_date : String,

    @SerializedName("vote_average")
    val vote_average : Float,

    var isBookmarked: Boolean = false

) : Parcelable/*{
    constructor() : this("", "", "", "")
}*/

