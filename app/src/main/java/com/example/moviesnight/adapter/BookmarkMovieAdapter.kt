package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.*
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.model.Movie
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class BookmarkMovieAdapter(
    private var bookmarkMovies: MutableList<Movie>,
    val bInterface: MovieClickListener
) :
    RecyclerView.Adapter<BookmarkMovieAdapter.BookmarkMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMovieViewHolder {
        return BookmarkMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_movie_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: BookmarkMovieViewHolder, position: Int) {
        holder.bindItem(bookmarkMovies[position])
    }

    override fun getItemCount(): Int {
        return bookmarkMovies.size
    }


    inner class BookmarkMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookmarkMovieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        private val imageBase = "https://image.tmdb.org/t/p/w500/"

        init {
            itemView.findViewById<ProgressBar>(R.id.rMovieItemProgress).visibility = View.GONE
            bookmarkMovieImageView = itemView.findViewById(R.id.recyclerMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
            itemView.setOnClickListener {
                bInterface.onMovieItemClick(it, bookmarkMovies[layoutPosition])
                Log.d("myApp", "item ${bookmarkMovies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                    bookmarkMovies[layoutPosition].isBookmarked = false
                    bookmarkedMovies.remove(bookmarkMovies[layoutPosition])
                    bookmarkMovies.remove(bookmarkMovies[layoutPosition])
                    notifyItemRemoved(layoutPosition)
            }
        }

        fun bindItem(anItem: Movie) {
            Picasso.get().load(imageBase + anItem.posterPath).into(bookmarkMovieImageView)

            if (anItem.isBookmarked) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}