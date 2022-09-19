package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.ErrorCallback
import com.example.moviesnight.`interface`.GenreCallback
import com.example.moviesnight.`interface`.MovieCallback
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.adapter.GenreAdapter
import com.example.moviesnight.adapter.RMovieAdapter
import com.example.moviesnight.adapter.SMovieAdapter
import com.example.moviesnight.model.Movie
import com.example.moviesnight.network.Networking
import kotlin.math.abs

class HomeFragment : Fragment(), MovieClickListener {
    private lateinit var listener: MovieClickListener
    private var trendingPage=1
    private var genrePage=1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        listener = this

        //configuring slider animation settings
        val cpt = CompositePageTransformer()
        cpt.addTransformer(MarginPageTransformer(40))
        cpt.addTransformer { passedView: View, fl: Float ->
            val r = 1 - abs(fl)
            passedView.scaleY = 0.85f + r * 0.15f
        }

        ////////////// Now Trending Movies //////////////
        //Fetching data from internet
        val nowTrendingMoviesRecycler = view.findViewById<ViewPager2>(R.id.nowTrendingMoviesSlider)
        val nowTrendingMoviesProgress =
            view.findViewById<ProgressBar>(R.id.nowTrendingMoviesProgress)
        val nowTrendingSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                nowTrendingMoviesProgress.visibility = View.GONE
                nowTrendingMoviesRecycler.adapter = SMovieAdapter(movies, this)
            }
        }
        val nowTrendingFailure = ErrorCallback {
            nowTrendingMoviesProgress.visibility = View.GONE
            val errorTextView = view.findViewById<TextView>(R.id.sliderMovieError)
            errorTextView.visibility = View.VISIBLE
        }
        //Configuring viewpager settings
        Networking.getTrendingMovieData(nowTrendingSuccess, nowTrendingFailure,trendingPage)
        nowTrendingMoviesRecycler.clipToPadding = false
        nowTrendingMoviesRecycler.clipChildren = false
        nowTrendingMoviesRecycler.offscreenPageLimit = 3
        nowTrendingMoviesRecycler.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        nowTrendingMoviesRecycler.setPageTransformer(cpt)
        ////////////// Now Trending Movies //////////////
        nowTrendingMoviesRecycler.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if(!nowTrendingMoviesRecycler.canScrollHorizontally(1)){
                    Networking.getTrendingMovieData(nowTrendingSuccess, nowTrendingFailure,trendingPage)
                    Log.d("myApp","Cann't right scroll horizontally")
                }
                if(!nowTrendingMoviesRecycler.canScrollHorizontally(-1)){
                    Log.d("myApp","Cann't left scroll horizontally")
                }
            }
        })
        ////////////// Genre Movies //////////////
        val genreMovieRecycler = view.findViewById<RecyclerView>(R.id.genreMoviesRecycler)

        ////////////// Genres //////////////
        val genreRecycler = view.findViewById<ViewPager2>(R.id.genreSlider)
        val genreProgressBar = view.findViewById<ProgressBar>(R.id.genreProgress)
        var genreAdapter: GenreAdapter
        var genreId: Int
        val onClickListener = this
        val genreSuccess = GenreCallback { genres ->
            if (!genres.isNullOrEmpty()) {
                genreProgressBar.visibility = View.GONE
                genreAdapter = GenreAdapter(genres)
                genreRecycler.adapter = genreAdapter
                genreRecycler.registerOnPageChangeCallback(object : ViewPager2
                .OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        genreId = genreAdapter.getCurrentItemID(genreRecycler.currentItem)
                        val genreMovieSuccess = MovieCallback { movies ->
                            if (!movies.isNullOrEmpty()) {
                                genreMovieRecycler.adapter = RMovieAdapter(movies, onClickListener)
                            }
                        }
                        Networking.getGenreMovieData(genreMovieSuccess, {}, genreId)
                    }
                })
            }
        }
        //configuring genres slider settings
        Networking.getGenreData(genreSuccess) {}
        genreRecycler.clipToPadding = false
        genreRecycler.clipChildren = false
        genreRecycler.offscreenPageLimit = 5
        genreRecycler.setPageTransformer(cpt)
        genreRecycler.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        ////////////// Genres //////////////
        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        Log.d("myApp","${movieItem.movieID} sss")
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.homeToDetail, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }
}