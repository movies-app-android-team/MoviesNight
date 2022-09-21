package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.adapter.BookmarkMovieAdapter
import com.example.moviesnight.bookmarkedMovies
import com.example.moviesnight.model.Movie
import io.paperdb.Paper

class BookmarkMoviesFragment : Fragment(), MovieClickListener {
    private lateinit var bookmarkRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmark_movies, container, false)

        //adding dummy icons to a recycler view
        bookmarkRecycler = view.findViewById(R.id.bookmarkRecycler)
        val bookmarkAdapter = BookmarkMovieAdapter(bookmarkedMovies, this)
        bookmarkRecycler.adapter = BookmarkMovieAdapter(bookmarkedMovies, this)
        bookmarkAdapter.notifyDataSetChanged()
        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", /*movieItem.isBookmarked*/Paper.book().read<Int>("${movieItem.movieID}")==1)
        findNavController().navigate(R.id.bookmarksToDetails, x)
        Log.d("myApp", "movie with ID: ${movieItem.movieID} clicked")
    }
}