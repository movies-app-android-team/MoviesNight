package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.*
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
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


class SearchFragment : Fragment(), MovieClickListener {
    private lateinit var listener: MovieClickListener
    private lateinit var resultsRecyclerProgressBar: ProgressBar
    private lateinit var searchResultsRecycler: RecyclerView
    private lateinit var errorText: TextView
    private lateinit var adapter: RMovieAdapter
    private lateinit var scrollView: NestedScrollView

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
        scrollView=view.findViewById(R.id.searchNestedScroll)
        errorText = view.findViewById(R.id.searchErrorText)
        var page=1
        searchTab.setOnTouchListener(OnTouchListener { _, event ->
            val rightDrawable = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= searchTab.right - searchTab.compoundDrawables[rightDrawable].bounds.width()
                ) {
                    //handle the search icon click
                    page=1
                    searchFirstPage(searchTab.text.toString())
                    return@OnTouchListener true
                }
            }
            false
        })
        searchTab.setOnKeyListener(OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //handle the enter key click
                page=1
                searchFirstPage(searchTab.text.toString())
                return@OnKeyListener true
            }
            false
        })

        //Pagnation
        scrollView.setOnScrollChangeListener(object :NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                if(scrollY==v.getChildAt(0).measuredHeight-v.measuredHeight){
                    Log.d("Page","Cann't scroll down")
                    page++
                    searchNextPage(searchTab.text.toString(),page)
                }
            }
        })

        ////////////// Search Results recycler //////////////

        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putString("posterPath", movieItem.posterPath)
        findNavController().navigate(R.id.searchToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    private fun searchFirstPage(z: String) {
        resultsRecyclerProgressBar.visibility = VISIBLE
        val moviesCallback = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                resultsRecyclerProgressBar.visibility = INVISIBLE
                searchResultsRecycler.visibility = VISIBLE
                errorText.visibility = INVISIBLE
                //To do try to improve performance by reusing same object
                adapter = RMovieAdapter(movies, this, null)
                searchResultsRecycler.adapter = adapter
                scrollView.scrollTo(0,0)
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
        Networking.getSearchData(moviesCallback, errorCallback, z,1)
    }
    private fun searchNextPage(z: String,page:Int) {
        Log.d("Search Page", page.toString())
        resultsRecyclerProgressBar.visibility = VISIBLE
        val moviesCallback = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                resultsRecyclerProgressBar.visibility = INVISIBLE
                searchResultsRecycler.visibility = VISIBLE
                errorText.visibility = INVISIBLE

                adapter.appendList(movies)
                adapter.notifyItemRangeChanged(page * 20, 20)

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
        Networking.getSearchData(moviesCallback, errorCallback, z, page)
    }
}