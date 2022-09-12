package com.example.moviesnight.recycler


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView

class SimilarMovieAdapter(private val similarMovies: List<SimilarMovieItem>) :
    RecyclerView.Adapter<SimilarMovieAdapter.SimilarMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMovieViewHolder {
        return SimilarMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.similar_movie_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarMovieViewHolder, position: Int) {
        holder.bindItem(similarMovies[position])
    }

    override fun getItemCount(): Int {
        return similarMovies.size
    }

    inner class SimilarMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val similarMovieImageView: RoundedImageView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            similarMovieImageView = itemView.findViewById(R.id.similarMovieImage)
            itemView.setOnClickListener {
                // code goes here
                Log.d("myApp", "item ${similarMovies[layoutPosition]} clicked")
            }
        }

        fun bindItem(anItem: SimilarMovieItem) {
            similarMovieImageView.setImageResource(anItem.similarMovieImage)
        }
    }
}