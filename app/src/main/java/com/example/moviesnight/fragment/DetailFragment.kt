package com.example.moviesnight.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.Recycler.MovieListAdapter
import com.example.moviesnight.recycler.MovieListItem

class DetailFragment : Fragment() {
    private lateinit var appNavigator: AppNavigator
    private lateinit var moviesList:RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        moviesList = view.findViewById(R.id.similarMoviesRecycler)
        val genreMovies = mutableListOf<MovieListItem>()
        genreMovies.add(MovieListItem("Good Father",1,2020,R.drawable.test2,4.2f))
        genreMovies.add(MovieListItem("Hi",2,2020,R.drawable.test2,4.2f))
        genreMovies.add(MovieListItem("Hello",3,2020,R.drawable.test2,4.2f))
        genreMovies.add(MovieListItem("&&",20,2020,R.drawable.test2,10f))
        genreMovies.add(MovieListItem(":-)",40,2020,R.drawable.test2,0f))

        moviesList.adapter= MovieListAdapter(genreMovies)

        return view
    }
}