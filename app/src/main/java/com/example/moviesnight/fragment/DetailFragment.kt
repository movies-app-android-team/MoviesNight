package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.DetailedMovieCallback
import com.example.moviesnight.`interface`.ErrorCallback
import com.example.moviesnight.`interface`.MovieCallback
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.adapter.RMovieAdapter
import com.example.moviesnight.model.Movie
import com.example.moviesnight.network.Networking
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(), MovieClickListener {
    private val imageBase = "https://image.tmdb.org/t/p/w500/"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        //adding dummy data to similar movies recycler
        val similarMoviesRecycler = view.findViewById<RecyclerView>(R.id.similarMoviesRecycler)
        val bookmarkStatus = view.findViewById<ImageView>(R.id.movieDetailsBookmarkStatus)
        val movieImage = view.findViewById<RoundedImageView>(R.id.movieDetailImageView)
        val movieRating = view.findViewById<TextView>(R.id.ratingValue)
        val movieDuration = view.findViewById<TextView>(R.id.durationValue)
        val movieOverview = view.findViewById<TextView>(R.id.overView)
        val loadingProgressBar = view.findViewById<ProgressBar>(R.id.movieDetailsProgressBar)
        val similarMovierRcycler = view.findViewById<RecyclerView>(R.id.similarMoviesRecycler)
        val similarMoviesLoadingBar=view.findViewById<ProgressBar>(R.id.similarMoviesProgress)

        var isBookmarked = requireArguments().getBoolean("isBookmarked")
        val movieID = requireArguments().getInt("movieID")

        val movieSuccess = DetailedMovieCallback { movie ->
            loadingProgressBar.visibility = View.GONE
            Picasso.get().load(imageBase + movie.posterPath).into(movieImage)
            movieRating.text = movie.voteAverage.toString()
            movieDuration.text = movie.runTime.toString()
            movieYear.text = movie.year
            movieOverview.text = movie.overview.toString()
        }
        //Configuring viewpager settings
        Networking.getMovieDetails(movieSuccess, {}, movieID)

        val similarMovieSuccess = MovieCallback { movies ->
            if (!movies.isNullOrEmpty()) {
                similarMoviesLoadingBar.visibility = View.GONE
                similarMovierRcycler.adapter = RMovieAdapter(movies, this)
            }
        }
        val similarMovieFailure = ErrorCallback {
            similarMoviesLoadingBar.visibility = View.GONE
            val errorTextView = view.findViewById<TextView>(R.id.sliderMovieError)
            errorTextView.visibility = View.VISIBLE
        }
        //Configuring viewpager settings
        Networking.getSimilarMovieData(similarMovieSuccess, similarMovieFailure,movieID)

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
//        val similarMovies = mutableListOf<RMovieItem>()
//        similarMovies.add(RMovieItem(1, R.drawable.test2))
//        similarMovies.add(RMovieItem(2, R.drawable.test2))
//        similarMovies.add(RMovieItem(3, R.drawable.test2))
//        similarMovies.add(RMovieItem(4, R.drawable.test2))
//        similarMovies.add(RMovieItem(5, R.drawable.test2))
//        similarMovies.add(RMovieItem(6, R.drawable.test2))
//        similarMoviesRecycler.adapter = RMovieAdapter(similarMovies, this)

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
}