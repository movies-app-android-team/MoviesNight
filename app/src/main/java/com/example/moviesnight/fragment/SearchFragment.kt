package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.*
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.ErrorCallback
import com.example.moviesnight.`interface`.MovieCallback
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.adapter.RMovieAdapter
import com.example.moviesnight.model.Movie
import com.example.moviesnight.network.Networking
import io.paperdb.Paper


class SearchFragment : Fragment(), MovieClickListener {
    private lateinit var listener: MovieClickListener
    private lateinit var resultsRecyclerProgressBar: ProgressBar
    private lateinit var searchResultsRecycler: RecyclerView
    private lateinit var errorText: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        listener = this

        //configuring search bar settings
        val searchTab = view.findViewById<EditText>(R.id.searchTab)
        searchResultsRecycler = view.findViewById(R.id.searchResultsRecycler)
        resultsRecyclerProgressBar = view.findViewById(R.id.searchRecyclerProgress)
        errorText = view.findViewById(R.id.searchErrorText)
        searchTab.setOnTouchListener(OnTouchListener { _, event ->
            val rightDrawable = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= searchTab.right - searchTab.compoundDrawables[rightDrawable].bounds.width()
                ) {
                    //handle the search icon click
                    search(searchTab.text.toString())
                    return@OnTouchListener true
                }
            }
            false
        })
        searchTab.setOnKeyListener(OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //handle the enter key click
                search(searchTab.text.toString())
                return@OnKeyListener true
            }
            false
        })

        ////////////// Search Results recycler //////////////

        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", /*movieItem.isBookmarked*/Paper.book().read<Int>("${movieItem.movieID}")==1)
        findNavController().navigate(R.id.searchToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    private fun search(z: String) {
        resultsRecyclerProgressBar.visibility = VISIBLE
        val moviesCallback = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                resultsRecyclerProgressBar.visibility = INVISIBLE
                searchResultsRecycler.visibility = VISIBLE
                errorText.visibility = INVISIBLE
                searchResultsRecycler.adapter = RMovieAdapter(movies, this)
                return@MovieCallback
            }
            resultsRecyclerProgressBar.visibility = INVISIBLE
            searchResultsRecycler.visibility = INVISIBLE
            errorText.visibility = VISIBLE
        }
        val errorCallback = ErrorCallback {
            resultsRecyclerProgressBar.visibility = INVISIBLE
            searchResultsRecycler.visibility = INVISIBLE
            errorText.visibility = VISIBLE
        }
        Networking.getSearchData(moviesCallback, errorCallback, z)
    }
}