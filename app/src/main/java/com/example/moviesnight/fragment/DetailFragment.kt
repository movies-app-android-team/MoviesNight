package com.example.moviesnight.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.moviesnight.ErrorCallback
import com.example.moviesnight.MovieCallback
import com.example.moviesnight.MovieNetworking
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.models.DetailedCallback
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.recycler.RMovieAdapter
import com.example.moviesnight.recycler.Movie
import com.example.moviesnight.slider.GenreAdapter
import com.example.moviesnight.slider.SMovieAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(), RItemClickListener {
    private val imageBase = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        //adding dummy data to similar movies recycler
//        val similarMoviesRecycler = view.findViewById<RecyclerView>(R.id.similarMoviesRecycler)
        val bookmarkStatus = view.findViewById<ImageView>(R.id.movieDetailsBookmarkStatus)

        var isBookmarked = requireArguments().getBoolean("isBookmarked")
        setBookmarkIcon(isBookmarked, bookmarkStatus)
        bookmarkStatus.setOnClickListener {
            if(isBookmarked) {
                isBookmarked = false
                bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                //handle un bookmarking here
                return@setOnClickListener
            }
            isBookmarked = true
            bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
        }
//        val overviewMovies = view.findViewById<ViewPager2>(R.id.overviewText)
//        val detailImageMovies = view.findViewById<ViewPager2>(R.id.movieDetailRImageView)

        val movieID = requireArguments().getInt("movieID")
//        val tv = view.findViewById<TextView>(R.id.ratingValue)
//        tv.text = arguments?.getString("movieID")
       /* val genreRecycler = view.findViewById<ViewPager2>(R.id.genreSlider)*/
//        val view = inflater.inflate(R.layout.fragment_detail, container, false)
//        val bookmarkStatus: ImageView = view.findViewById(R.id.movieDetailsBookmarkStatus)
//        val backDrop: ImageView = view.findViewById(R.id.movieDetailBackdrop)
        val poster: RoundedImageView = view.findViewById(R.id.movieDetailRImageView)
//        val title: TextView = view.findViewById(R.id.movieDetailTitle)
        val rating: TextView = view.findViewById(R.id.ratingValue)
//        val Genre: TextView = view.findViewById(R.id.movieDetailReleaseDatePlusGenre)
        val year: TextView = view.findViewById(R.id.yearValue)
        val duration: TextView = view.findViewById(R.id.durationValue)
        val overView: TextView = view.findViewById(R.id.overviewText)


        val detailCallback= DetailedCallback { movie ->

            if (movie != null) {
//                Picasso.get().load(imageBase + movie.backdropPath).into(backDrop)
                Picasso.get().load(imageBase + movie.posterPath).into(poster)
//                title.text = movie.movieTitle
                rating.text = (movie.voteAverage / 2).toString()
                year.text = movie.year
                duration.text = "${movie.runTime} minutes"
                overView.text = movie.overview


 //               similarMoviesRecycler.adapter = RMovieAdapter(movies,this)
//                tv.text = arguments?.getString("movieID")
//                overviewMovies.adapter = RMovieAdapter(movies,this)
//                detailImageMovies.adapter = RMovieAdapter(movies,this)
            }
        }
        val detailErrorCallback= ErrorCallback {

            Toast.makeText(requireContext(), "Error loading movies", Toast.LENGTH_SHORT).show()
        }
        MovieNetworking.getDetailList(detailCallback,detailErrorCallback,movieID)
//        MovieNetworking.getSimilarList(detailCallback,detailErrorCallback,movieID)

        val similarMoviesRecycler = view.findViewById<RecyclerView>(R.id.similarMoviesRecycler)
        val moviesCallback= MovieCallback { movies ->

            if (!movies.isNullOrEmpty()) {
                similarMoviesRecycler.adapter = RMovieAdapter(movies, this)

            }
        }
        val errorCallback= ErrorCallback {

            Toast.makeText(requireContext(), "Error loading movies", Toast.LENGTH_SHORT).show()
        }
        MovieNetworking.getSimilarList(moviesCallback,errorCallback, movieID )

//        val similarMovies = mutableListOf<Movie>()
//        similarMovies.add(Movie(1, R.drawable.test2))
//        similarMovies.add(Movie(2, R.drawable.test2))
//        similarMovies.add(Movie(3, R.drawable.test2))
//        similarMovies.add(Movie(4, R.drawable.test2))
//        similarMovies.add(Movie(5, R.drawable.test2))
//        similarMovies.add(Movie(6, R.drawable.test2))
//        similarMoviesRecycler.adapter = RMovieAdapter(similarMovies, this)

        val backBTN = view.findViewById<FloatingActionButton>(R.id.back_btn)
        backBTN.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }

    override fun onRMovieItemClick(view: View, movieItem: Moviee) {
        val x = Bundle()
        movieItem.id?.let { x.putInt("movieID", it) }
//        x.putString("movieID", movieItem.id)
//        x.putString("overview", movieItem.overview)
        x.putBoolean("isBookmarked", movieItem.isBookmarked)
        findNavController().navigate(R.id.detailsToDetails, x)
        Log.d("myApp", "omg item clicked fr fr ${movieItem.id}")
    }

    private fun setBookmarkIcon(x: Boolean, y:ImageView) {
        if(x)
            y.setImageResource(R.drawable.ic_bookmarked)
        else
            y.setImageResource(R.drawable.ic_un_bookmarked)
    }
}