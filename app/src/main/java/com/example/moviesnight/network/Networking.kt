package com.example.moviesnight.network

import android.util.Log
import com.example.moviesnight.`interface`.ApiService
import com.example.moviesnight.`interface`.ErrorCallback
import com.example.moviesnight.`interface`.GenreCallback
import com.example.moviesnight.`interface`.MovieCallback
import com.example.moviesnight.model.Genre
import com.example.moviesnight.model.GenreResponse
import com.example.moviesnight.model.Movie
import com.example.moviesnight.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    private const val BASE_URL = "https://api.themoviedb.org/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesService = retrofit.create(ApiService::class.java)
    fun getTrendingMovieData(callback: MovieCallback, errorCallback: ErrorCallback? = null) {
        moviesService.getTrendingMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Movie>? = response.body()?.movies
                println(response.body()?.movies.toString() + "sss")
                callback.onMoviesReady(movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })
    }

    fun getGenreMovieData(callback: MovieCallback, errorCallback: ErrorCallback? = null,genre:String) {
        moviesService.getGenreMovieList(genre).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Movie>? = response.body()?.movies
                println(response.body()?.movies.toString() + "sss")
                callback.onMoviesReady(movies)
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })
    }
    fun getSearchData(callback: MovieCallback, errorCallback: ErrorCallback? = null, search: String) {
        moviesService.getSearchList(search).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Movie>? = response.body()?.movies
                callback.onMoviesReady(movies)
                Log.d("myApp", response.body()?.movies.toString())
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })
    }

    fun getGenreData(callback: GenreCallback, errorCallback: ErrorCallback?=null){
        moviesService.getGenreList().enqueue(object :Callback<GenreResponse>{
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                val genres: List<Genre>? = response.body()?.genres
                callback.onGenreReady(genres)
                Log.d("myApp", response.body()?.genres.toString())
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }

        })
    }

}