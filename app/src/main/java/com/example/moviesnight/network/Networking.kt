package com.example.moviesnight.network

import android.util.Log
import com.example.moviesnight.`interface`.*
import com.example.moviesnight.model.*
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
    fun getTrendingMovieData(
        callback: MovieCallback,
        errorCallback: ErrorCallback? = null,
        page: Int
    ) {
        moviesService.getTrendingMovieList(page).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Movie>? = response.body()?.movies
                Log.d("myApp", response.body()?.movies.toString() + "sss")
                callback.onMoviesReady(movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })
    }

    fun getGenreMovieData(
        callback: MovieCallback,
        errorCallback: ErrorCallback? = null,
        genre: Int,
        page:Int
    ) {
        moviesService.getGenreMovieList(genre,page).enqueue(object : Callback<MovieResponse> {
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

    fun getSearchData(
        callback: MovieCallback,
        errorCallback: ErrorCallback? = null,
        search: String,
        page:Int
    ) {
        moviesService.getSearchList(search,page).enqueue(object : Callback<MovieResponse> {
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

    fun getGenreData(callback: GenreCallback, errorCallback: ErrorCallback? = null) {
        moviesService.getGenreList().enqueue(object : Callback<GenreResponse> {
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

    fun getMovieDetails(
        callback: DetailedMovieCallback,
        errorCallback: ErrorCallback? = null,
        movieID: Int
    ) {
        moviesService.getMovieByID(movieID).enqueue(object : Callback<DetailedMovie> {
            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                val movie = response.body()
                Log.d("myApp", "Movie details \n $response$movieID")
                if (movie != null) {
                    callback.onMovieReady(movie)
                }
            }

            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }

        })
    }

    fun getSimilarMovieData(
        callback: MovieCallback,
        errorCallback: ErrorCallback? = null,
        ID: Int
    ) {
        moviesService.getSimilarList(ID).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Movie>? = response.body()?.movies
                callback.onMoviesReady(movies)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })
    }


}