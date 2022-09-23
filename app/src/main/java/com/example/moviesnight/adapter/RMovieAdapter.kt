package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.makeramen.roundedimageview.RoundedImageView
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.bookmarkedMovies
//import com.example.moviesnight.contains
import com.example.moviesnight.model.Movie
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.paperdb.Paper
import java.lang.Exception

class RMovieAdapter(private var movies: List<Movie>, val rInterface: MovieClickListener) :
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
    fun mergeList(newList:List<Movie>){
        movies=movies+newList
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        private val imageBase = "https://image.tmdb.org/t/p/w500/"
        private val progressBar: ProgressBar
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.recyclerMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.recyclerMoviesBookmarkStatus)
            progressBar = itemView.findViewById(R.id.rMovieItemProgress)
            itemView.setOnClickListener {
                rInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
               if(movies[layoutPosition].isBookmarked == true){
                        movies[layoutPosition].isBookmarked = false
                        Paper.book().delete(movies[layoutPosition].movieID.toString())
                        bookmarkedMovies.remove(movies[layoutPosition])
                        bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                } else {
                        movies[layoutPosition].isBookmarked = true
                        Paper.book().write<Boolean>(movies[layoutPosition].movieID.toString(),true)
                        Log.d("Paper2","added to paper listener ${movies[layoutPosition].movieID.toString()}")
                        Log.d("Paper2","check added to paper listener${Paper.book().read<Boolean>("${movies[layoutPosition].movieID}")==true})}")

                        bookmarkedMovies.add(movies[layoutPosition])
                    bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                }
            }
        }

        fun bindItem(anItem: Movie) {
            Picasso.get().load(imageBase + anItem.posterPath)
                .into(movieImageView, object : Callback {
                    override fun onSuccess() {
                        progressBar.visibility = View.GONE
                    }
                    override fun onError(e: Exception?) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(itemView.context, "Error loading movie", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            if (anItem.isBookmarked) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}