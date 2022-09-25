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
import io.paperdb.Paper

class BookmarkMovieAdapter(
    private val bookmarkMovies: List<Movie>,
    val bInterface: MovieClickListener
) :
    RecyclerView.Adapter<BookmarkMovieAdapter.BookmarkMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkMovieViewHolder {
        return BookmarkMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_movie_item_layout, parent, false)
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
                val found = containsMovie(bookmarkedMovies, bookmarkMovies[layoutPosition].movieID)
                if (found.first) {
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    Paper.book().delete("${found.second!!.movieID}")
                    bookmarkedMovies.remove(found.second!!)
                    notifyItemRemoved(layoutPosition)
                }
            }
        }

        fun bindItem(anItem: Movie) {
            val found = Paper.book()
                .read<Boolean>("${anItem.movieID}") == true
            Picasso.get().load(imageBase + anItem.posterPath).into(bookmarkMovieImageView)
            if (found) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}