package com.example.moviesnight.recycler

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.GItemClickListener
import com.example.moviesnight.`interface`.RItemClickListener
import com.example.moviesnight.models.Moviee
import com.example.moviesnight.models.MoviesGenre
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class MoviesGenreAdaptor
    (private val moviesGenre: List<MoviesGenre>, val gInterface: GItemClickListener):
    RecyclerView.Adapter<MoviesGenreAdaptor.MovieGenreItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieGenreItemViewHolder {
            return MovieGenreItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_movie_item_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MovieGenreItemViewHolder, position: Int) {
            holder.bindItem(moviesGenre[position])

        }

        override fun getItemCount(): Int {
            return moviesGenre.size
        }

        inner class MovieGenreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val movieImageView: RoundedImageView
            private val bookmarkStatus: ImageView
            private lateinit var mySharedPreferences: SharedPreferences
            //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)
            private val IMAGE_BASE="https://image.tmdb.org/t/p/w500/"
            init {
                movieImageView = itemView.findViewById(R.id.recyclerMovieImage)
                bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
                itemView.setOnClickListener {
//                rInterface.onRMovieItemClick(it, movies[layoutPosition])
//                Log.d("myApp", "item ${movies[layoutPosition]} clicked")
                }
                bookmarkStatus.setOnClickListener {
//                if(movies[layoutPosition].isBookmarked)
//                    movies[layoutPosition].isBookmarked = false
//                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
//                    Log.d("myApp", "unBookmarked")
//                    //handle un bookmarking here
//                    return@setOnClickListener
//                movies[layoutPosition].isBookmarked = true
//                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
//                //handle bookmarking here
//                Log.d("myApp", "bookmarked")
                }

            }

            fun bindItem(anItem: MoviesGenre) {
                Picasso.get().load(IMAGE_BASE+anItem.poster_path).into(movieImageView)
                if(anItem.isBookmarked) {
                    bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                    return
                }
                bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
            }
        }
}