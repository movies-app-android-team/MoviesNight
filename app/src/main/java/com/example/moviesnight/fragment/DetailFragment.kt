package com.example.moviesnight.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.recycler.SimilarMovieAdapter
import com.example.moviesnight.recycler.SimilarMovieItem

class DetailFragment : Fragment() {
    private lateinit var appNavigator: AppNavigator
    private lateinit var similarMoviesRecycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        similarMoviesRecycler = view.findViewById(R.id.similarMoviesRecycler)
        val similarMovies = mutableListOf<SimilarMovieItem>()
        similarMovies.add(SimilarMovieItem(R.drawable.test2, 1))
        similarMovies.add(SimilarMovieItem(R.drawable.test2, 2))
        similarMovies.add(SimilarMovieItem(R.drawable.test2, 3))
        similarMovies.add(SimilarMovieItem(R.drawable.test2, 4))
        similarMovies.add(SimilarMovieItem(R.drawable.test2, 4))
        similarMoviesRecycler.adapter = SimilarMovieAdapter(similarMovies)
        return view
    }
}