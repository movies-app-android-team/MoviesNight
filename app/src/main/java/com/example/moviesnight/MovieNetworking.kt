package com.example.moviesnight

import com.example.moviesnight.models.*
import com.example.moviesnight.services.MovieApiServices
import com.example.moviesnight.services.MovieInterfaceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieNetworking {


    private  const val BASE_URL = "https://api.themoviedb.org/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val moviesService = retrofit.create(MovieInterfaceApi::class.java)

    fun getMovieData(callback: MovieCallback, errorCallback: ErrorCallback? = null) {
        moviesService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Moviee>? = response.body()?.movies
                println(response.body()?.movies.toString()+"sss")
                callback.onMoviesReady(movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}

        fun  getSearchData(callback: MovieCallback, errorCallback: ErrorCallback? = null, search : String) {
            moviesService.getSearchList(search).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    val movies: List<Moviee>? = response.body()?.movies
                    println(response.body()?.movies.toString()+"sss")
                    callback.onMoviesReady(movies)

                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    errorCallback?.onError(t)
                }
            })
    }

    fun getBookMarkData(callback: MovieCallback, errorCallback: ErrorCallback? = null) {
        moviesService.getBookMarkList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val bookMovies: List<Moviee>? = response.body()?.movies
                println(response.body()?.movies.toString()+"sss")
                callback.onMoviesReady(bookMovies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}
    fun getGenreData(callback: GenreCallback, errorCallback: ErrorCallback? = null) {
        moviesService.getGenreList().enqueue(object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                val movies: List<Genre>? = response.body()?.genres
                println(response.body()?.genres.toString()+"sss")
                callback.onGenresReady(movies)

            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}
    fun getMoviesGenreData(callback: MovieCallback, errorCallback: ErrorCallback? = null,genreID: Int) {
        moviesService.getActionGenreList(genreID).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Moviee>? = response.body()?.movies
                println(response.body()?.movies.toString()+"sss")
                callback.onMoviesReady(movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}
    fun getSimilarList(callback: MovieCallback, errorCallback: ErrorCallback? = null,MovieId:Int) {
        moviesService.getSimilarList(MovieId).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies: List<Moviee>? = response.body()?.movies
                println(response.body()?.movies.toString()+"sss")
                callback.onMoviesReady(movies)

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}
    fun getDetailList(callback: DetailedCallback, errorCallback: ErrorCallback? = null,genreID:Int) {
        moviesService.getDetailList(genreID).enqueue(object : Callback<DetailedMovie> {
            override fun onResponse(call: Call<DetailedMovie>, response: Response<DetailedMovie>) {
                val movies: DetailedMovie? = response.body()
                println(response.body().toString()+"sss")
                callback.onMovieReady(movies)
            }

            override fun onFailure(call: Call<DetailedMovie>, t: Throwable) {
                t.printStackTrace()
                errorCallback?.onError(t)
            }
        })}
}