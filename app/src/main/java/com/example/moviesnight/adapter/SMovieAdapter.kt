package com.example.moviesnight.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesnight.R
import com.example.moviesnight.`interface`.MovieClickListener
import com.example.moviesnight.bookmarkedMovies
import com.example.moviesnight.model.Movie
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import io.paperdb.Paper

class SMovieAdapter(private var movies: List<Movie>, val sInterface: MovieClickListener) :
    RecyclerView.Adapter<SMovieAdapter.MovieItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slider_movie_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bindItem(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun appendList(newMovies: List<Movie>) {
        val current = movies + newMovies
        movies = current
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        private val imageBase = "https://image.tmdb.org/t/p/w500/"
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.sliderMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.nowTrendingMoviesBookmarkStatus)
            itemView.setOnClickListener {
                sInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "now trending item ${movies[layoutPosition]} clicked")
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
            Picasso.get().load(imageBase + anItem.posterPath).into(movieImageView)
            if(anItem.movieID==810693){Log.d("Paper2","${anItem.movieID} is bookmarked ${anItem.isBookmarked}")
            Log.d("Paper2","bind ${Paper.book().read<Boolean>("${movies[layoutPosition].movieID}")==true})}")}
            if (/*Paper.book().read<Boolean>(anItem.movieID.toString())==true*/anItem.isBookmarked==true) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }

    }
}