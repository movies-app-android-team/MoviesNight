package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.recycler.RMovieAdapter
import com.example.moviesnight.recycler.RMovieItem
import com.example.moviesnight.slider.GenreAdapter
import com.example.moviesnight.slider.GenreItem
import com.example.moviesnight.slider.SMovieAdapter
import com.example.moviesnight.slider.SMovieItem
import kotlin.math.abs

class HomeFragment : Fragment(), RItemClickListener {
    private lateinit var viewPager: ViewPager2
    private lateinit var genreViewPager: ViewPager2
    private lateinit var genreMovieRecycler: RecyclerView
    lateinit var listener: RItemClickListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = view.findViewById(R.id.nowTrendingMoviesSlider)
        genreViewPager = view.findViewById(R.id.genreSlider)
        genreMovieRecycler = view.findViewById(R.id.genreMoviesRecycler)
        listener = this

        // setting list of movies to slide
        val items = mutableListOf<SMovieItem>()
        items.add(SMovieItem(1, R.drawable.test2))
        items.add(SMovieItem(2, R.drawable.test2))
        items.add(SMovieItem(3, R.drawable.test2))
        items.add(SMovieItem(4, R.drawable.test2))

        val genres = mutableListOf<GenreItem>()
        genres.add(GenreItem("Action", 21))
        genres.add(GenreItem("Thriller", 22))
        genres.add(GenreItem("Drama", 23))
        genres.add(GenreItem("Horror", 24))
        genres.add(GenreItem("Comedy", 25))
        genres.add(GenreItem("Sci-Fi", 26))

        val genreMovies = mutableListOf<RMovieItem>()
        genreMovies.add(RMovieItem(1, R.drawable.test2))
        genreMovies.add(RMovieItem(2, R.drawable.test2))
        genreMovies.add(RMovieItem(3, R.drawable.test2))
        genreMovies.add(RMovieItem(4, R.drawable.test2))
        genreMovies.add(RMovieItem(5, R.drawable.test2))
        genreMovies.add(RMovieItem(6, R.drawable.test2))

        genreMovieRecycler.adapter = RMovieAdapter(genreMovies, listener)


        viewPager.adapter = SMovieAdapter(items)
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.offscreenPageLimit = 3
        viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val cpt = CompositePageTransformer()
        cpt.addTransformer(MarginPageTransformer(40))
        cpt.addTransformer { passedView: View, fl: Float ->
            val r = 1 - abs(fl)
            passedView.scaleY = 0.85f + r * 0.15f
        }
        viewPager.setPageTransformer(cpt)
        //image slider


        genreViewPager.adapter = GenreAdapter(genres)
        genreViewPager.clipToPadding = false
        genreViewPager.clipChildren = false
        genreViewPager.offscreenPageLimit = 5
        genreViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("myApp", "genre ${genres[position].genreName} selected")
            }
        })
        genreViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        genreViewPager.setPageTransformer(cpt)
        //image slider

        return view
    }

    override fun onRMovieItemClick(view: View, movieItem: RMovieItem) {
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

}