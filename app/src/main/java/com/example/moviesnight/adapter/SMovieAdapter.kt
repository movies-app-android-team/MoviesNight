package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.bookmarkedMovies
import com.example.moviesnight.contains
import com.example.moviesnight.model.Movie
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class SMovieAdapter(private var movies: List<Movie>, val sInterface: MovieClickListener) :
    RecyclerView.Adapter<SMovieAdapter.MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slider_movie_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun appendList(newMovies: List<Movie>) {
        val current = movies + newMovies
        movies = current
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        private val imageBase = "https://image.tmdb.org/t/p/w500/"
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.sliderMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.nowTrendingMoviesBookmarkStatus)
            itemView.setOnClickListener {
                sInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "now trending item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                val found = contains(bookmarkedMovies, movies[layoutPosition].movieID)
                if (found.first) {
                    movies[layoutPosition].isBookmarked = false
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    bookmarkedMovies.remove(found.second!!)
                    notifyBookmarks()
                } else {
                    bookmarkedMovies.add(movies[layoutPosition])
                    movies[layoutPosition].isBookmarked = true
                    bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                    notifyBookmarks()
                }
            }
        }

        fun bindItem(anItem: Movie) {
            Picasso.get().load(imageBase + anItem.posterPath).into(movieImageView)
            if (anItem.isBookmarked) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }

    }
}