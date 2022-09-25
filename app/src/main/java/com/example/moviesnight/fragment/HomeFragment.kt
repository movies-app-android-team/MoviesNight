package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
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
import com.example.moviesnight.model.Genre
import com.example.moviesnight.model.Movie
import com.example.moviesnight.network.Networking

class HomeFragment : Fragment(), MovieClickListener {
    private lateinit var listener: MovieClickListener
    private var trendingPage = 1


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
            val r = 1 - kotlin.math.abs(fl)
            passedView.scaleY = 0.85f + r * 0.15f
        }
        ////////////// Now Trending Movies //////////////
        //Fetching data from internet
        val nowTrendingMoviesRecycler = view.findViewById<ViewPager2>(R.id.nowTrendingMoviesSlider)
        val nowTrendingMoviesProgress =
            view.findViewById<ProgressBar>(R.id.nowTrendingMoviesProgress)
        var trendingAdapter = SMovieAdapter(emptyList(), this, null)
        var genreMovieAdapter = RMovieAdapter(emptyList(), this, null)
        val nowTrendingSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                nowTrendingMoviesProgress.visibility = View.GONE
                trendingAdapter = SMovieAdapter(movies, this, genreMovieAdapter)
                nowTrendingMoviesRecycler.adapter = trendingAdapter

                nowTrendingMoviesRecycler.registerOnPageChangeCallback(object :
                    ViewPager2.OnPageChangeCallback() {

                    @SuppressLint("NotifyDataSetChanged")
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                        if (!nowTrendingMoviesRecycler.canScrollHorizontally(1)) {

                            trendingPage += 1
                            val nowTrendingNewPageSuccess = MovieCallback { movies ->
                                if (!movies.isNullOrEmpty()) {
                                    Log.d("myApp", "hi $trendingPage")
                                    trendingAdapter.appendList(movies)
                                    trendingAdapter.notifyDataSetChanged()
                                }
                            }
                            Networking.getTrendingMovieData(
                                nowTrendingNewPageSuccess,
                                {},
                                trendingPage
                            )
                            Log.d(
                                "myApp",
                                "Can't right scroll horizontally,this is page $trendingPage"
                            )
                        }
                        if (!nowTrendingMoviesRecycler.canScrollHorizontally(-1)) {
                            Log.d("myApp", "Can't left scroll horizontally")
                        }
                    }
                })
            }
        }
        if (savedInstanceState != null) {
            Log.d("myApp", "${savedInstanceState.getInt("scroll")}")
        }
        val nowTrendingFailure = ErrorCallback {
            nowTrendingMoviesProgress.visibility = View.GONE
            val errorTextView = view.findViewById<TextView>(R.id.sliderMovieError)
            errorTextView.visibility = View.VISIBLE
        }
        //Configuring viewpager settings
        Networking.getTrendingMovieData(nowTrendingSuccess, nowTrendingFailure, trendingPage)
        nowTrendingMoviesRecycler.clipToPadding = false
        nowTrendingMoviesRecycler.clipChildren = false
        nowTrendingMoviesRecycler.offscreenPageLimit = 3
        nowTrendingMoviesRecycler.setPageTransformer(cpt)
        nowTrendingMoviesRecycler.getChildAt(0)
            .overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        ////////////// Now Trending Movies //////////////

        ////////////// Genre Movies //////////////
        val genreMovieRecycler = view.findViewById<RecyclerView>(R.id.genreMoviesRecycler)

        ////////////// Genres //////////////
        val genreRecycler = view.findViewById<ViewPager2>(R.id.genreSlider)
        val genreProgressBar = view.findViewById<ProgressBar>(R.id.genreProgress)
        val nestedScrollView = view.findViewById<NestedScrollView>(R.id.nestedScrollView)
        var genreAdapter: GenreAdapter
        var genreId: Int
        var page = 1
        val onClickListener = this
        val genreSuccess = GenreCallback { genres ->
            if (!genres.isNullOrEmpty()) {
                genreProgressBar.visibility = View.GONE
                genreAdapter = GenreAdapter(genresWithArrows(genres))
                genreRecycler.adapter = genreAdapter
                genreRecycler.registerOnPageChangeCallback(object : ViewPager2
                .OnPageChangeCallback() {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        genreId = genreAdapter.getCurrentItemID(genreRecycler.currentItem)
                        val genreMovieSuccess = MovieCallback { movies ->
                            if (!movies.isNullOrEmpty()) {
                                genreMovieAdapter =
                                    RMovieAdapter(movies, onClickListener, trendingAdapter)
                                genreMovieRecycler.adapter = genreMovieAdapter
                                trendingAdapter.rAdapter = genreMovieAdapter
                                nestedScrollView.setOnScrollChangeListener(NestedScrollView
                                    .OnScrollChangeListener { v, _, scrollY, _, _ ->
                                    if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                                        page++
                                        //fetch next page data with handler
                                        fetchNextGenreMoviePage(genreMovieAdapter, genreId, page)
                                    }
                                })
                            }
                        }
                        Networking.getGenreMovieData(genreMovieSuccess, {}, genreId, page)
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
        x.putString("posterPath", movieItem.posterPath)
        findNavController().navigate(R.id.homeToDetail, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    private fun genresWithArrows(x: List<Genre>): List<Genre> {
        x[0].genreName = "${x[0].genreName}   \u2192"
        for (i in 1 until x.size - 1) {
            x[i].genreName = "\u2190  ${x[i].genreName}   \u2192"
        }
        x[x.size - 1].genreName = "\u2190   ${x[x.size - 1].genreName}"
        return x
    }

    private fun fetchNextGenreMoviePage(genreMovieAdapter: RMovieAdapter, genreId: Int, page: Int) {
        val genreMovieSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                Log.d("Page", "End of page,new page= $page")
                genreMovieAdapter.appendList(movies)
                genreMovieAdapter.notifyItemRangeChanged(page * 20, 20)
            }
        }
        Networking.getGenreMovieData(genreMovieSuccess, {}, genreId, page)
    }
}