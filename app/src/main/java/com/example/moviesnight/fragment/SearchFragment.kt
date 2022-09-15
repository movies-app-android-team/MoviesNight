package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.PassedMovie
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.recycler.RMovieAdapter
import com.example.moviesnight.recycler.RMovieItem


class SearchFragment : Fragment(), RItemClickListener {
    private lateinit var listener: RItemClickListener

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        listener = this

        //configuring search bar settings
        val searchTab = view.findViewById<EditText>(R.id.searchTab)
        searchTab.setOnTouchListener(OnTouchListener { _, event ->
            val rightDrawable = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= searchTab.right - searchTab.compoundDrawables[rightDrawable].bounds.width()
                ) {
                    //handle the search icon click
                    Log.d("myApp", "search icon clicked")
                    return@OnTouchListener true
                }
            }
            false
        })
        searchTab.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //handle the enter key click
                Log.d("myApp", "enter button clicked")
                return@OnKeyListener true
            }
            false
        })

        ////////////// Search Results recycler //////////////
        val searchMovies = mutableListOf<RMovieItem>()
        searchMovies.add(RMovieItem(1, R.drawable.test2))
        searchMovies.add(RMovieItem(2, R.drawable.test2))
        searchMovies.add(RMovieItem(3, R.drawable.test2))
        searchMovies.add(RMovieItem(4, R.drawable.test2))
        searchMovies.add(RMovieItem(5, R.drawable.test2))
        searchMovies.add(RMovieItem(6, R.drawable.test2))
        val searchResultRecycler = view.findViewById<RecyclerView>(R.id.searchResultsRecycler)
        searchResultRecycler.adapter = RMovieAdapter(searchMovies, listener)
        ////////////// Search Results recycler //////////////

        return view
    }

    override fun onRMovieItemClick(view: View, movieItem: RMovieItem) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.searchToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }
}