package com.example.moviesnight.slider

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R

class GenreAdapter(private val genres: List<GenreItem>) :
    RecyclerView.Adapter<GenreAdapter.GenreItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreItemViewHolder {
        return GenreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.genre_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreItemViewHolder, position: Int) {
        holder.bindItem(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    inner class GenreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreItemView: TextView
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            genreItemView = itemView.findViewById(R.id.slidedGenre)
            itemView.setOnClickListener {
                // code goes here
                Log.d("myApp", "item ${genres[layoutPosition]} clicked")
            }
        }

        fun bindItem(anItem: GenreItem) {
            genreItemView.text = anItem.genreName
        }
    }
}