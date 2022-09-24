package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.bookmarkedMovies
import com.example.moviesnight.containsIndex
import com.example.moviesnight.containsMovie
import com.example.moviesnight.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.paperdb.Paper
import java.lang.Exception


class RMovieAdapter(
    var movies: List<Movie>,
    val rInterface: MovieClickListener,
    val sAdapter: SMovieAdapter?
) :
    RecyclerView.Adapter<RMovieAdapter.MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie_item_layout, parent, false)
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
        private val progressBar: ProgressBar

        init {
            movieImageView = itemView.findViewById(R.id.recyclerMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
            progressBar = itemView.findViewById(R.id.rMovieItemProgress)
            itemView.setOnClickListener {
                rInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                val foundMovie = containsMovie(bookmarkedMovies, movies[layoutPosition].movieID)
                if (foundMovie.first) {
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    bookmarkedMovies.remove(foundMovie.second)
                    Paper.book().delete("${movies[layoutPosition].movieID}")
                } else {
                    bookmarkedMovies.add(movies[layoutPosition])
                    bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                    Paper.book().write("${movies[layoutPosition].movieID}", true)
                }
                notifyItem(movies[layoutPosition].movieID)
            }
        }

        private fun notifyItem(movieID: Int) {
            if (sAdapter == null || sAdapter.movies.isEmpty())
                return
            val itemIndex = containsIndex(sAdapter.movies as MutableList<Movie>, movieID)
            if (itemIndex != -1) {
                sAdapter.notifyItemChanged(itemIndex)
            }
        }

        fun bindItem(anItem: Movie) {
            val found = Paper.book()
                .read<Boolean>("${movies[layoutPosition].movieID}") == true
            Picasso.get().load(imageBase + anItem.posterPath)
                .into(movieImageView, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                    }
                })
            if (found) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}