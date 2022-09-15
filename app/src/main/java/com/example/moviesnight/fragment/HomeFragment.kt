package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.`interface`.SItemClickListener
import com.example.moviesnight.recycler.RMovieAdapter
import com.example.moviesnight.recycler.RMovieItem
import com.example.moviesnight.slider.GenreAdapter
import com.example.moviesnight.slider.GenreItem
import com.example.moviesnight.slider.SMovieAdapter
import com.example.moviesnight.slider.SMovieItem
import kotlin.math.abs

class HomeFragment : Fragment(), RItemClickListener, SItemClickListener {
    private lateinit var listener: RItemClickListener

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
        //setting now trending movies list
        val nowTrendingMovies = mutableListOf<SMovieItem>()
        nowTrendingMovies.add(SMovieItem(1, R.drawable.test2))
        nowTrendingMovies.add(SMovieItem(2, R.drawable.test2))
        nowTrendingMovies.add(SMovieItem(3, R.drawable.test2))
        nowTrendingMovies.add(SMovieItem(4, R.drawable.test2))

        //configuring now trending movies slider settings
        val nowTrendingMoviesRecycler = view.findViewById<ViewPager2>(R.id.nowTrendingMoviesSlider)
        nowTrendingMoviesRecycler.adapter = SMovieAdapter(nowTrendingMovies, this)
        nowTrendingMoviesRecycler.clipToPadding = false
        nowTrendingMoviesRecycler.clipChildren = false
        nowTrendingMoviesRecycler.offscreenPageLimit = 3
        nowTrendingMoviesRecycler.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        nowTrendingMoviesRecycler.setPageTransformer(cpt)
        ////////////// Now Trending Movies //////////////


        ////////////// Genres //////////////
        //setting genres list
        val genres = mutableListOf<GenreItem>()
        genres.add(GenreItem("Action", 21))
        genres.add(GenreItem("Thriller", 22))
        genres.add(GenreItem("Drama", 23))
        genres.add(GenreItem("Horror", 24))
        genres.add(GenreItem("Comedy", 25))
        genres.add(GenreItem("Sci-Fi", 26))

        //configuring genres slider settings
        val genreRecycler = view.findViewById<ViewPager2>(R.id.genreSlider)
        genreRecycler.adapter = GenreAdapter(genres)
        genreRecycler.clipToPadding = false
        genreRecycler.clipChildren = false
        genreRecycler.offscreenPageLimit = 5
        genreRecycler.setPageTransformer(cpt)
        genreRecycler.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        genreRecycler.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("myApp", "genre ${genres[position].genreName} selected")
            }
        })
        ////////////// Genres //////////////

        ////////////// Genre Movies //////////////
        val genreMovies = mutableListOf<RMovieItem>()
        genreMovies.add(RMovieItem(1, R.drawable.test2))
        genreMovies.add(RMovieItem(2, R.drawable.test2))
        genreMovies.add(RMovieItem(3, R.drawable.test2))
        genreMovies.add(RMovieItem(4, R.drawable.test2))
        genreMovies.add(RMovieItem(5, R.drawable.test2))
        genreMovies.add(RMovieItem(6, R.drawable.test2))

        val genreMovieRecycler = view.findViewById<RecyclerView>(R.id.genreMoviesRecycler)
        genreMovieRecycler.adapter = RMovieAdapter(genreMovies, listener)
        ////////////// Genre Movies //////////////
        return view
    }

    override fun onRMovieItemClick(view: View, movieItem: RMovieItem) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.homeToDetail, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    override fun onSMovieItemClick(view: View, movieItem: SMovieItem) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.homeToDetail, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }
}