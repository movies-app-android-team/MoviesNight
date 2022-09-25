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
import com.example.moviesnight.containsIndex
import com.example.moviesnight.containsMovie
import com.example.moviesnight.model.Movie
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import io.paperdb.Paper

class SMovieAdapter(
    var movies: List<Movie>,
    val sInterface: MovieClickListener,
    var rAdapter: RMovieAdapter?
) :
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

        init {
            movieImageView = itemView.findViewById(R.id.sliderMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.nowTrendingMoviesBookmarkStatus)
            itemView.setOnClickListener {
                sInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "now trending item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                val found = containsMovie(bookmarkedMovies, movies[layoutPosition].movieID)
                if (found.first) {
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    bookmarkedMovies.remove(found.second!!)
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
            if (rAdapter == null || rAdapter!!.movies.isEmpty())
                return
            val itemIndex = containsIndex(rAdapter!!.movies as MutableList<Movie>, movieID)
            if (itemIndex != -1) {
                rAdapter!!.notifyItemChanged(itemIndex)
            }
        }

        fun bindItem(anItem: Movie) {
            val found = Paper.book()
                .read<Boolean>("${anItem.movieID}") == true
            Picasso.get().load(imageBase + anItem.posterPath).into(movieImageView)
            if (found) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}