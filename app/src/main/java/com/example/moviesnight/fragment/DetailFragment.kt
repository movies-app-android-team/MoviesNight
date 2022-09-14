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
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.recycler.RMovieAdapter
import com.example.moviesnight.recycler.RMovieItem

class DetailFragment : Fragment(), RItemClickListener{
    private lateinit var similarMoviesRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        similarMoviesRecycler = view.findViewById(R.id.similarMoviesRecycler)
        val similarMovies = mutableListOf<RMovieItem>()
        similarMovies.add(RMovieItem(1, R.drawable.test2))
        similarMovies.add(RMovieItem(2, R.drawable.test2))
        similarMovies.add(RMovieItem(3, R.drawable.test2))
        similarMovies.add(RMovieItem(4, R.drawable.test2))
        similarMovies.add(RMovieItem(5, R.drawable.test2))
        similarMovies.add(RMovieItem(6, R.drawable.test2))
        similarMoviesRecycler.adapter = RMovieAdapter(similarMovies, this)
        return view
    }

    override fun onRMovieItemClick(view: View, movieItem: RMovieItem) {

        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)

        findNavController().navigate(R.id.detailsToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }
}