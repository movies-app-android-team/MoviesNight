package com.example.moviesnight.bookmarks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView

class BookmarkMovieAdapter(private val bookmarkMovies: List<BookmarkMovieItem>) :
    RecyclerView.Adapter<BookmarkMovieAdapter.BookmarkMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMovieViewHolder {
        return BookmarkMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.bookmark_movie_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarkMovieViewHolder, position: Int) {
        holder.bindItem(bookmarkMovies[position])
    }

    override fun getItemCount(): Int {
        return bookmarkMovies.size
    }

    inner class BookmarkMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookmarkMovieImageView: RoundedImageView
        private val bookmarkMovieName: TextView
        private val bookmarkMovieGenre: TextView
        private val bookmarkMovieRating: TextView


        init {
            bookmarkMovieImageView = itemView.findViewById(R.id.bookmarkMovieImage)
            bookmarkMovieName = itemView.findViewById(R.id.bookmarkMovieName)
            bookmarkMovieGenre = itemView.findViewById(R.id.bookmarkMovieGenre)
            bookmarkMovieRating = itemView.findViewById(R.id.bookmarkMovieRating)
            Log.d("myApp", "item $layoutPosition created")
            itemView.setOnClickListener {
                // code goes here
                Log.d("myApp", "item ${bookmarkMovies[layoutPosition]} clicked")
            }
        }

        fun bindItem(anItem: BookmarkMovieItem) {
            bookmarkMovieImageView.setImageResource(anItem.movieImage)
            bookmarkMovieName.text = anItem.movieName
            bookmarkMovieGenre.text = anItem.movieGenre
            bookmarkMovieRating.text = "${anItem.movieRating}"
        }
    }
}