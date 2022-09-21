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

    fun appendList(newMovies:List<Movie>){
        val current=movies+newMovies
        movies=current
    }

    inner class MovieItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieImageView: RoundedImageView
        private val bookmarkStatus: ImageView
        private val imageBase="https://image.tmdb.org/t/p/w500/"
        //private val myIntent: Intent = Intent(itemView.context, TestClass::class.java)

        init {
            movieImageView = itemView.findViewById(R.id.sliderMovieImage)
            bookmarkStatus = itemView.findViewById(R.id.nowTrendingMoviesBookmarkStatus)
            itemView.setOnClickListener {
                sInterface.onMovieItemClick(it, movies[layoutPosition])
                Log.d("myApp", "now trending item ${movies[layoutPosition]} clicked")
            }
            bookmarkStatus.setOnClickListener {
                Log.d("Paper1 selected","${movies[layoutPosition].movieID}")
                Log.d("Paper1","${Paper.book().read<Int>("${movies[layoutPosition].movieID}")}")
                if(/*movies[layoutPosition].isBookmarked*/Paper.book().read<Int>("${movies[layoutPosition].movieID}")==1) {
                    movies[layoutPosition].isBookmarked = false
                    Paper.book().delete("${movies[layoutPosition].movieID}")
                    bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
                    bookmarkedMovies.remove(movies[layoutPosition])
                    //handle un bookmarking here
                    return@setOnClickListener
                }
                movies[layoutPosition].isBookmarked = true
                Paper.book().write("${movies[layoutPosition].movieID}",1)
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                bookmarkedMovies.add(movies[layoutPosition])
            }
        }

        fun bindItem(anItem: Movie) {
            Picasso.get().load(imageBase+anItem.posterPath).into(movieImageView)
            Log.d("PaperBind","${movies[layoutPosition].movieID}")
            Log.d("Paper","${Paper.book().read<Int>("${movies[layoutPosition].movieID}")}")
            if(/*anItem.isBookmarked*/Paper.book().read<Int>("${movies[layoutPosition].movieID}")==1) {
                bookmarkStatus.setImageResource(R.drawable.ic_bookmarked)
                return
            }
            bookmarkStatus.setImageResource(R.drawable.ic_un_bookmarked)
        }
    }
}