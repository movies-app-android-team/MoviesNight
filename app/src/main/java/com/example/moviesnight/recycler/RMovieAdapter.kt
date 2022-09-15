package com.example.moviesnight.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView
import com.example.moviesnight.`interface`.RItemClickListener

class RMovieAdapter(private val movies: List<RMovieItem>, val rInterface: RItemClickListener) :
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

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.recyclerMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
            itemView.setOnClickListener {
                rInterface.onRMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                if(movies[layoutPosition].isBookmarked) {
                    movies[layoutPosition].isBookmarked = false
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    Log.d("myApp", "unBookmarked")
                    //handle un bookmarking here
                    return@setOnClickListener
                }
                movies[layoutPosition].isBookmarked = true
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                //handle bookmarking here
                Log.d("myApp", "bookmarked")
            }
        }

        fun bindItem(anItem: RMovieItem) {
            movieImageView.setImageResource(anItem.imageID)
            if(anItem.isBookmarked) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}