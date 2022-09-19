package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.model.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment(), MovieClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        //adding dummy data to similar movies recycler
        val similarMoviesRecycler = view.findViewById<RecyclerView>(R.id.similarMoviesRecycler)
        val bookmarkStatus = view.findViewById<ImageView>(R.id.movieDetailsBookmarkStatus)

        var isBookmarked = requireArguments().getBoolean("isBookmarked")
        setBookmarkIcon(isBookmarked, bookmarkStatus)
        bookmarkStatus.setOnClickListener {
            if(isBookmarked) {
                isBookmarked = false
                bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                //handle un bookmarking here
                return@setOnClickListener
            }
            isBookmarked = true
            bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
        }
//        val similarMovies = mutableListOf<RMovieItem>()
//        similarMovies.add(RMovieItem(1, R.drawable.test2))
//        similarMovies.add(RMovieItem(2, R.drawable.test2))
//        similarMovies.add(RMovieItem(3, R.drawable.test2))
//        similarMovies.add(RMovieItem(4, R.drawable.test2))
//        similarMovies.add(RMovieItem(5, R.drawable.test2))
//        similarMovies.add(RMovieItem(6, R.drawable.test2))
//        similarMoviesRecycler.adapter = RMovieAdapter(similarMovies, this)

        val backBTN = view.findViewById<FloatingActionButton>(R.id.back_btn)
        backBTN.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.detailsToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    private fun setBookmarkIcon(x: Boolean, y:ImageView) {
        if(x)
            y.setImageResource(R.drawable.ic_bookmarked)
        else
            y.setImageResource(R.drawable.ic_un_bookmarked)
    }
}