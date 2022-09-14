package com.example.moviesnight.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.recyclerMovieImage)
            itemView.setOnClickListener {
                rInterface.onRMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "item ${movies[layoutPosition]} clicked")
            }
        }

        fun bindItem(anItem: RMovieItem) {
            movieImageView.setImageResource(anItem.imageID)
        }
    }
}