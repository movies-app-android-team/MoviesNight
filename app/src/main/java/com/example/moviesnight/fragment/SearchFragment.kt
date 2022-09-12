package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.recycler.GenreMovieAdapter
import com.example.moviesnight.recycler.GenreMovieItem


class SearchFragment : Fragment() {
    private lateinit var searchResultRecycler: RecyclerView
    private lateinit var searchTab: EditText

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchResultRecycler = view.findViewById(R.id.searchResultsRecycler)
        searchTab = view.findViewById(R.id.searchTab)

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

        val searchMovies = mutableListOf<GenreMovieItem>()
        searchMovies.add(GenreMovieItem("Good Father", 1, 2020, R.drawable.test2, 4.2f))
        searchMovies.add(GenreMovieItem("Hi", 2, 2020, R.drawable.test2, 4.2f))
        searchMovies.add(GenreMovieItem("Hello", 3, 2020, R.drawable.test2, 4.2f))
        searchMovies.add(GenreMovieItem("&&", 20, 2020, R.drawable.test2, 10f))
        searchMovies.add(GenreMovieItem(":-)", 40, 2020, R.drawable.test2, 0f))

        searchResultRecycler.adapter = GenreMovieAdapter(searchMovies)

        return view
    }
}