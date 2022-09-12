package com.example.moviesnight.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.recycler.GenreMovieAdapter
import com.example.moviesnight.recycler.GenreMovieItem

class SearchFragment : Fragment() {
    private lateinit var searchResultRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_search, container, false)
        searchResultRecycler=view.findViewById(R.id.searchResultsRecycler)

        val searchMovies = mutableListOf<GenreMovieItem>()
        searchMovies.add(GenreMovieItem("Good Father",1,2020,R.drawable.test2,4.2f))
        searchMovies.add(GenreMovieItem("Hi",2,2020,R.drawable.test2,4.2f))
        searchMovies.add(GenreMovieItem("Hello",3,2020,R.drawable.test2,4.2f))
        searchMovies.add(GenreMovieItem("&&",20,2020,R.drawable.test2,10f))
        searchMovies.add(GenreMovieItem(":-)",40,2020,R.drawable.test2,0f))

        searchResultRecycler.adapter= GenreMovieAdapter(searchMovies)

        return view
    }
}