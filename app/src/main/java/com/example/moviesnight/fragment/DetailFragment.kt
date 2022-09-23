package com.example.moviesnight.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.DetailedMovieCallback
import com.example.moviesnight.`interface`.MovieCallback
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.adapter.RMovieAdapter
import com.example.moviesnight.model.Genre
import com.example.moviesnight.model.Movie
import com.example.moviesnight.network.Networking
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(), MovieClickListener {
    private val imageBase = "https://image.tmdb.org/t/p/w500/"
    private lateinit var similarMovies:RecyclerView
    private lateinit var scrollView: NestedScrollView

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val bookmarkStatus: ImageView = view.findViewById(R.id.movieDetailsBookmarkStatus)
        val backDrop: ImageView = view.findViewById(R.id.movieDetailBackdrop)
        val poster: ImageView = view.findViewById(R.id.movieDetailPoster)
        val title: TextView = view.findViewById(R.id.movieDetailTitle)
        val rating: RatingBar = view.findViewById(R.id.ratingValue)
        val yearGenre: TextView = view.findViewById(R.id.movieDetailReleaseDatePlusGenre)
        val duration: TextView = view.findViewById(R.id.durationValue)
        val overView: TextView = view.findViewById(R.id.overView)
        var page=1
        similarMovies = view.findViewById(R.id.similarMoviesRecycler)
        scrollView=view.findViewById(R.id.detailScroll)

        var isBookmarked = requireArguments().getBoolean("isBookmarked")
        val movieID = requireArguments().getInt("movieID")

        val movieSuccess = DetailedMovieCallback { movie ->
            Picasso.get().load(imageBase + movie.backdropPath).into(backDrop)
            Picasso.get().load(imageBase + movie.posterPath).into(poster)
            title.text = movie.movieTitle
            rating.rating = movie.voteAverage / 2
            yearGenre.text =
                "${movie.year.substringBefore("-")}${getGenreNames(movie.genres)}"
            duration.text = "${movie.runTime} minutes"
            overView.text = movie.overview
        }
        //Configuring viewpager settings
        Networking.getMovieDetails(movieSuccess, {}, movieID)
        var y=scrollView.isHorizontalScrollBarEnabled
        scrollView.setOnScrollChangeListener(object :NestedScrollView.OnScrollChangeListener{
            override fun onScrollChange(v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                scrollX
                v.getChildAt(0).measuredWidth
                v.measuredWidth
                var x=scrollX
                if(scrollX==v.getChildAt(0).measuredWidth-v.measuredWidth){
                    Log.d("Similar","Camm't scroll right")
                }
            }
        })

        val similarMovieSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                similarMovies.adapter = RMovieAdapter(movies, this)
            }
        }
        Networking.getSimilarMovieData(similarMovieSuccess, {}, movieID,1)

        setBookmarkIcon(isBookmarked, bookmarkStatus)
        bookmarkStatus.setOnClickListener {
            if (isBookmarked) {
                isBookmarked = false
                bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                //handle un bookmarking here
                return@setOnClickListener
            }
            isBookmarked = true
            bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
        }
        val backBTN = view.findViewById<FloatingActionButton>(R.id.back_btn)
        backBTN.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }

    override fun onMovieItemClick(view: View, movieItem: Movie) {
        val x = Bundle()
        x.putInt("movieID", movieItem.movieID)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.detailsToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.movieID}")
    }

    private fun setBookmarkIcon(x: Boolean, y: ImageView) {
        if (x)
            y.setImageResource(R.drawable.ic_bookmarked)
        else
            y.setImageResource(R.drawable.ic_un_bookmarked)
    }

    private fun getGenreNames(x: List<Genre>): String {
        val dot = " \u2022 "
        if(x.isEmpty())
            return ""
        if(x.size>1) {
            var y = x[0].genreName
            for(i in 1 until x.size) {
                y = "$y$dot${x[i].genreName}"
            }
            return " $dot$y"
        }
        return "$dot${x[0].genreName}"
    }

    private fun getNextPageSimilarMovies(movieID:Int,page:Int){
        val similarMovieSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                similarMovies.adapter = RMovieAdapter(movies, this)
            }
        }
        Networking.getSimilarMovieData(similarMovieSuccess, {}, movieID,page)
    }

}