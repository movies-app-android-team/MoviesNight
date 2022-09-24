package com.example.moviesnight.services

import com.example.moviesnight.models.DetailedMovie
import com.example.moviesnight.models.GenreResponse
import com.example.moviesnight.models.MovieResponse
import com.example.moviesnight.models.MoviesGenreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieInterfaceApi {
    @GET("/3/movie/popular?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getMovieList(): Call<MovieResponse>

    @GET("/3/search/movie?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getSearchList(@Query("query")query:String) : Call<MovieResponse>

    @GET("/3/movie/popular?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getBookMarkList() : Call<MovieResponse>

    @GET("/3/genre/movie/list?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getGenreList() : Call<GenreResponse>

    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getActionGenreList(@Query("with_genres")ID:Int): Call<MovieResponse>

    @GET("/3/movie/{movie_id}/similar?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getSimilarList(@Path("movie_id")ID:Int): Call<MovieResponse>

    @GET("/3/movie/{movie_id}?api_key=823cec0678552086f9eb5cbdce233bbe")
    fun getDetailList(@Path("movie_id")ID:Int): Call<DetailedMovie>


//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=12")
//    fun getAdventureGenreList(): Call<MovieResponse>
//
//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=16")
//    fun getAnimationGenreList(): Call<MovieResponse>
//
//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=35")
//    fun getComedyGenreList(): Call<MovieResponse>
//
//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=80")
//    fun getCrimeGenreList(): Call<MovieResponse>
//
//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=99")
//    fun getDocumentaryGenreList(): Call<MovieResponse>
//
//    @GET("/3/discover/movie?api_key=823cec0678552086f9eb5cbdce233bbe&with_genres=18")
//    fun getDramaGenreList(): Call<MovieResponse>








}