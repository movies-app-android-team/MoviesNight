package com.example.moviesnight.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesGenre(
    @SerializedName("genre_ids")
    val genre_ids : String ,
    @SerializedName("name")
    val name : String ,
    @SerializedName("id")
    val id : String ?,

    @SerializedName("original_title")
    val original_title : String?,

    @SerializedName("poster_path")
    val poster_path : String?,

    var isBookmarked: Boolean = false
): Parcelable {
    constructor() : this("" ,"","","","")
}