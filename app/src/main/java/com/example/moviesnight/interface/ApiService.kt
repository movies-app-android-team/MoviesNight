package com.example.moviesnight.`interface`

import com.example.moviesnight.model.GenreResponse
import com.example.moviesnight.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiService {
    @GET("/3/trending/all/day?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getTrendingMovieList(): Call<MovieResponse>

    @GET("/3/search/movie?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getSearchList(@Query("query")query:String):Call<MovieResponse>

    @GET("/3/genre/movie/list?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getGenreList():Call<GenreResponse>

    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getGenreMovieList(@Query("with_genres")genre:String): Call<MovieResponse>
}