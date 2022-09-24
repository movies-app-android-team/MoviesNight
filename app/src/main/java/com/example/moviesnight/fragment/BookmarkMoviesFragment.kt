package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.ErrorCallback
import com.example.moviesnight.MovieCallback
import com.example.moviesnight.MovieNetworking
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.BItemClickListener
import com.example.moviesnight.bookmarks.BookmarkMovieAdapter
import com.example.moviesnight.bookmarks.BookmarkMovieItem
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.models.MovieResponse
import com.example.moviesnight.services.MovieApiServices
import com.example.moviesnight.services.MovieInterfaceApi
import com.example.moviesnight.slider.SMovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmarkMoviesFragment : Fragment(), BItemClickListener {
    private lateinit var bookmarkRecycler: RecyclerView
    private lateinit var listener: BItemClickListener
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark_movies, container, false)
        listener = this
        //adding dummy icons to a recycler view
        bookmarkRecycler = view.findViewById(R.id.bookmarkRecycler)
        val moviesCallback= MovieCallback { movies ->

            if (!movies.isNullOrEmpty()) {
               bookmarkRecycler.adapter = BookmarkMovieAdapter(movies, this)

            }
        }
        val errorCallback= ErrorCallback {

            Toast.makeText(requireContext(), "Error loading movies", Toast.LENGTH_SHORT).show()
        }
        MovieNetworking.getBookMarkData(moviesCallback,errorCallback)
//        val bookmarkMovies = mutableListOf<BookmarkMovieItem>()
//        bookmarkMovies.add(
//            BookmarkMovieItem(
//                1,
//                R.drawable.test2,
//                "Fast and Furious",
//                "Action",
//                4.2f
//            )
//        )
//        bookmarkMovies.add(BookmarkMovieItem(2, R.drawable.test2, "Tomb Raider", "Action", 0f))
//        bookmarkMovies.add(BookmarkMovieItem(3, R.drawable.test2, "Maze Runner", "Action", 5.5f))
//        bookmarkMovies.add(
//            BookmarkMovieItem(
//                4,
//                R.drawable.test2,
//                "Fast and Furious",
//                "Sci-Fi",
//                4.2f
//            )
//        )
//        bookmarkMovies.add(
//            BookmarkMovieItem(
//                5,
//                R.drawable.test2,
//                "Fast and Furious",
//                "Action",
//                4.2f
//            )
//        )
//        bookmarkMovies.add(BookmarkMovieItem(1, R.drawable.test2, "Movie 1", "Genre 1", 1.1f))
//        bookmarkMovies.add(BookmarkMovieItem(2, R.drawable.test2, "Movie 2", "Genre 2", 2.2f))
//        bookmarkMovies.add(BookmarkMovieItem(3, R.drawable.test2, "Movie 3", "Genre 3", 2.2f))
//        bookmarkMovies.add(BookmarkMovieItem(4, R.drawable.test2, "Movie 4", "Genre 4", 2.2f))
//        bookmarkMovies.add(BookmarkMovieItem(5, R.drawable.test2, "Movie 5", "Genre 5", 2.2f))
//        bookmarkMovies.add(BookmarkMovieItem(6, R.drawable.test2, "Movie 6", "Genre 6", 2.2f))
//        bookmarkMovies.add(BookmarkMovieItem(7, R.drawable.test2, "Movie 7", "Genre 7", 2.2f))
//        bookmarkRecycler.adapter = BookmarkMovieAdapter(bookmarkMovies, this)

        return view
    }

    override fun onBMovieItemClick(view: View, movieItem: Moviee) {
        val x = Bundle()
        movieItem.id?.let { x.putInt("movieID", it) }
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.bookmarksToDetails, x)
        Log.d("myApp", "movie with ID: ${movieItem.id} clicked")
    }

//    private fun getMovieData(callback: (List<Moviee>) -> Unit) {
//        val apiService = MovieApiServices.getInstance().create(MovieInterfaceApi::class.java)
//        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                return callback(response.body()!!.movies)
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//
//            }
//        })
//
//    }
}