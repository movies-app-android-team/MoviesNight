package com.example.moviesnight.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.slider.SMovieAdapter
import com.example.moviesnight.slider.SMovieItem

class DetailFragment : Fragment() {
    private lateinit var similarMoviesRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        similarMoviesRecycler = view.findViewById(R.id.similarMoviesRecycler)
        val similarMovies = mutableListOf<SMovieItem>()
        similarMovies.add(SMovieItem(R.drawable.test2, 1))
        similarMovies.add(SMovieItem(R.drawable.test2, 2))
        similarMovies.add(SMovieItem(R.drawable.test2, 3))
        similarMovies.add(SMovieItem(R.drawable.test2, 4))
        similarMovies.add(SMovieItem(R.drawable.test2, 4))
        similarMoviesRecycler.adapter = SMovieAdapter(similarMovies)
        return view
    }
}