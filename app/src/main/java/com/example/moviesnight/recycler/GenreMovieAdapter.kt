package com.example.moviesnight.recycler


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.MainActivity
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView

class GenreMovieAdapter(private val genreMovies: List<GenreMovieItem>) :
    RecyclerView.Adapter<GenreMovieAdapter.GenreMovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreMovieViewHolder {
        return GenreMovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.genre_movie_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreMovieViewHolder, position: Int) {
        holder.bindItem(genreMovies[position])
    }

    override fun getItemCount(): Int {
        return genreMovies.size
    }

    inner class GenreMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreMovieText: TextView
        private val genreMovieImage: RoundedImageView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            genreMovieText = itemView.findViewById(R.id.genreMovieText)
            genreMovieImage = itemView.findViewById(R.id.genreMovieImage)
            itemView.setOnClickListener {
                // code goes here
                Log.d("myApp", "item ${genreMovies[layoutPosition]} clicked")
            }
        }

        @SuppressLint("SetTextI18n")
        fun bindItem(anItem: GenreMovieItem) {
            genreMovieText.text =
                "${anItem.genreMovieName}\n${anItem.genreMovieYear} | ${anItem.genreMovieRating}"
            genreMovieImage.setImageResource(anItem.genreMovieImage)
        }
    }
}